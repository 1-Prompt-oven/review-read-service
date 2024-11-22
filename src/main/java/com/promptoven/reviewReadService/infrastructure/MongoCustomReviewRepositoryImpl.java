package com.promptoven.reviewReadService.infrastructure;

import com.promptoven.reviewReadService.dto.in.web.ReviewRequestDto;
import com.promptoven.reviewReadService.dto.out.ReviewOutPaginationDto;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoCustomReviewRepositoryImpl {

    ReviewOutPaginationDto getReviewListByUserUuid(ReviewRequestDto reviewRequestDto);
}
