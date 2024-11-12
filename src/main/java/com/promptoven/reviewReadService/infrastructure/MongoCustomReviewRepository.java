package com.promptoven.reviewReadService.infrastructure;

import com.promptoven.reviewReadService.dto.out.ReviewOutPaginationDto;
import com.promptoven.reviewReadService.global.common.utils.CursorPage;

public interface MongoCustomReviewRepository {
    CursorPage<ReviewOutPaginationDto> getReviewListByUserUuid();
}
