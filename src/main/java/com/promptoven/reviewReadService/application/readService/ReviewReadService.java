package com.promptoven.reviewReadService.application.readService;

import com.promptoven.reviewReadService.dto.out.ReviewResponseDto;
import java.util.List;

public interface ReviewReadService {

    void testRequest();
    List<ReviewResponseDto> getReviews(String productUuid);
}
