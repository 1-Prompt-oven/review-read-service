package com.promptoven.reviewReadService.application.readService;

import com.promptoven.reviewReadService.dto.in.web.ReviewRequestDto;
import com.promptoven.reviewReadService.dto.out.ReviewOutPaginationDto;

public interface ReviewReadService {

    ReviewOutPaginationDto getReviews(ReviewRequestDto reviewRequestDto);
}
