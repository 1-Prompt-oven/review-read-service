package com.promptoven.reviewReadService.application.readService;

import com.promptoven.reviewReadService.document.ReviewReadDocument;
import com.promptoven.reviewReadService.dto.out.ReviewResponseDto;
import com.promptoven.reviewReadService.infrastructure.MongoReviewRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewReadServiceImpl implements ReviewReadService{

    private final MongoReviewRepository mongoReviewRepository;

    @Override
    public void testRequest() {
        mongoReviewRepository.save(new ReviewReadDocument("1", "1", "1", "1", "1"));
    }

    @Override
    public List<ReviewResponseDto> getReviews(String productUuid) {
        List<ReviewReadDocument> reviewReadDocumentList = mongoReviewRepository.findByProductUuid(productUuid);
        return ReviewResponseDto.toDtoList(reviewReadDocumentList);
    }
}
