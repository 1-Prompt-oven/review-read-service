package com.promptoven.reviewReadService.application.readService;

import com.promptoven.reviewReadService.document.ReviewReadDocument;
import com.promptoven.reviewReadService.dto.in.web.ReviewRequestDto;
import com.promptoven.reviewReadService.dto.out.ReviewOutPaginationDto;
import com.promptoven.reviewReadService.infrastructure.MongoCustomReviewRepositoryImpl;
import com.promptoven.reviewReadService.infrastructure.MongoReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewReadServiceImpl implements ReviewReadService {

    private final MongoCustomReviewRepositoryImpl mongoCustomReviewRepositoryImpl;
    private final MongoReviewRepository mongoReviewRepository;

    @Override
    public ReviewOutPaginationDto getReviews(ReviewRequestDto reviewRequestDto) {
        return mongoCustomReviewRepositoryImpl.getReviewListByUserUuid(reviewRequestDto);
    }
}
