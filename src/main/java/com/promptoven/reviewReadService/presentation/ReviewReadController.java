package com.promptoven.reviewReadService.presentation;

import com.promptoven.reviewReadService.application.readService.ReviewReadService;
import com.promptoven.reviewReadService.document.ReviewReadDocument;
import com.promptoven.reviewReadService.dto.in.message.CreateRequestMessageDto;
import com.promptoven.reviewReadService.dto.in.web.ReviewRequestDto;
import com.promptoven.reviewReadService.dto.out.ReviewOutPaginationDto;
import com.promptoven.reviewReadService.global.common.response.BaseResponse;
import com.promptoven.reviewReadService.global.common.utils.CursorPage;
import com.promptoven.reviewReadService.infrastructure.MongoReviewRepository;
import com.promptoven.reviewReadService.vo.out.ReadResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/review-read")
@RequiredArgsConstructor
public class ReviewReadController {

    private final ReviewReadService reviewReadService;
    
    @Operation(summary = "리뷰 조회 API", tags = {"리뷰"})
    @GetMapping
    public BaseResponse<CursorPage<ReadResponseVo>> getReviews(@RequestParam String productUuid,
            @RequestParam(required = false) LocalDateTime lastCreatedAt,
            @RequestParam(required = false) String lastId, @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer page) {

        ReviewRequestDto reviewRequestDto = ReviewRequestDto.toPaginationDto(productUuid, lastCreatedAt, lastId,
                pageSize, page);

        ReviewOutPaginationDto reviewOutPaginationDto = reviewReadService.getReviews(reviewRequestDto);

        return new BaseResponse<>(CursorPage.toCursorPage(reviewOutPaginationDto));
    }
}
