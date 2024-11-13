package com.promptoven.reviewReadService.presentation;

import com.promptoven.reviewReadService.application.readService.ReviewReadService;
import com.promptoven.reviewReadService.dto.in.ReviewRequestDto;
import com.promptoven.reviewReadService.dto.out.ReviewOutPaginationDto;
import com.promptoven.reviewReadService.global.common.utils.CursorPage;
import com.promptoven.reviewReadService.vo.out.ReadResponseVo;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/readReviews")
@RequiredArgsConstructor
public class ReviewReadController {

    private final ReviewReadService reviewReadService;

    @GetMapping
    public CursorPage<ReadResponseVo> getReviews(@RequestParam String productUuid,
            @RequestParam(required = false) LocalDateTime lastCreatedAt,
            @RequestParam(required = false) String lastId, @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) Integer page) {

        ReviewRequestDto reviewRequestDto = ReviewRequestDto.toPaginationDto(productUuid, lastCreatedAt, lastId,
                pageSize, page);

        ReviewOutPaginationDto reviewOutPaginationDto = reviewReadService.getReviews(reviewRequestDto);

        return CursorPage.toCursorPage(reviewOutPaginationDto);
    }
}
