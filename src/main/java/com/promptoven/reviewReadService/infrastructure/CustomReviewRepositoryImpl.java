package com.promptoven.reviewReadService.infrastructure;

import com.promptoven.reviewReadService.document.ReviewReadDocument;
import com.promptoven.reviewReadService.dto.in.ReviewRequestDto;
import com.promptoven.reviewReadService.dto.out.ReviewOutPaginationDto;
import com.promptoven.reviewReadService.dto.out.ReviewResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CustomReviewRepositoryImpl implements MongoCustomReviewRepositoryImpl {

    private final MongoTemplate mongoTemplate;

    private static final int DEFAULT_PAGE_SIZE = 20;
    private static final int DEFAULT_PAGE_NUMBER = 0;

    @Override
    public ReviewOutPaginationDto getReviewListByUserUuid(ReviewRequestDto reviewRequestDto) {

        String productUuid = reviewRequestDto.getProductUuid();
        LocalDateTime lastCreatedAt = reviewRequestDto.getLastCreatedAt();
        String lastId = reviewRequestDto.getLastId();
        int pageSize = Optional.ofNullable(reviewRequestDto.getPageSize()).orElse(DEFAULT_PAGE_SIZE);
        Integer page = Optional.ofNullable(reviewRequestDto.getPage()).orElse(DEFAULT_PAGE_NUMBER);

        Query query = new Query();

        // 필터: productUuid
        if (productUuid != null) {
            query.addCriteria(Criteria.where("productUuid").is(productUuid));
        }

        query.addCriteria(Criteria.where("isDeleted").is(false));

        // 페이징 커서 설정
        if (lastCreatedAt != null && lastId != null) {
            query.addCriteria(new Criteria().orOperator(
                    Criteria.where("createdAt").lt(lastCreatedAt),
                    new Criteria().andOperator(
                            Criteria.where("createdAt").is(lastCreatedAt),
                            Criteria.where("reviewId").lt(lastId)
                    )
            ));
        }

        // 정렬 및 제한
        query.with(Sort.by(Sort.Direction.DESC, "createdAt").and(Sort.by(Sort.Direction.DESC, "reviewId")));
        query.limit(pageSize + 1);  // 다음 페이지가 있는지 확인하기 위해 1개의 추가 데이터를 요청

        List<ReviewReadDocument> reviewReadDocuments = mongoTemplate.find(query, ReviewReadDocument.class);

        LocalDateTime nextCreatedAt = null;
        String nextReviewId = null;
        boolean hasNext = false;

        // 다음 페이지가 있는지 확인하고, 결과 리스트를 페이지 사이즈에 맞게 조정
        if (reviewReadDocuments.size() > pageSize) {
            hasNext = true;
            reviewReadDocuments = reviewReadDocuments.subList(0, pageSize);
            nextCreatedAt = reviewReadDocuments.get(pageSize - 1).getCreatedAt();
            nextReviewId = reviewReadDocuments.get(pageSize - 1).getId();
        }

        List<ReviewResponseDto> reviewOutPortDtoList = reviewReadDocuments.stream()
                .map(ReviewResponseDto::toDto)
                .collect(Collectors.toList());

        return ReviewOutPaginationDto.builder()
                .reviewResponseDtoList(reviewOutPortDtoList)
                .hasNext(hasNext)
                .lastCreatedAt(nextCreatedAt)
                .lastId(nextReviewId)
                .page(page+1)
                .pageSize(pageSize)
                .build();
    }
}
