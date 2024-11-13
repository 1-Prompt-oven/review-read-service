package com.promptoven.reviewReadService.application.readService;

import com.promptoven.reviewReadService.dto.in.ReviewRequestDto;
import com.promptoven.reviewReadService.dto.out.ReviewOutPaginationDto;
import com.promptoven.reviewReadService.infrastructure.MongoCustomReviewRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewReadServiceImpl implements ReviewReadService{

    private final MongoCustomReviewRepositoryImpl mongoCustomReviewRepositoryImpl;

    @Override
    public ReviewOutPaginationDto getReviews(ReviewRequestDto reviewRequestDto) {
        return mongoCustomReviewRepositoryImpl.getReviewListByUserUuid(reviewRequestDto);
    }
}
