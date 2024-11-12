package com.promptoven.reviewReadService.presentation;

import com.promptoven.reviewReadService.application.readService.ReviewReadService;
import com.promptoven.reviewReadService.dto.out.ReviewResponseDto;
import com.promptoven.reviewReadService.vo.out.ReadResponseVo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/readReviews")
@RequiredArgsConstructor
public class ReviewReadController {

    private final ReviewReadService reviewReadService;

    @PostMapping
    public void testRequest(){
        reviewReadService.testRequest();
    }

    @GetMapping("/{productUuid}")
    public List<ReadResponseVo> getReviews(@PathVariable String productUuid){
        List<ReviewResponseDto> reviewResponseDtoList = reviewReadService.getReviews(productUuid);
        return ReadResponseVo.toVoList(reviewResponseDtoList);
    }
}
