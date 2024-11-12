package com.promptoven.reviewReadService.presentation;

import com.promptoven.reviewReadService.application.readService.ReviewReadService;
import lombok.RequiredArgsConstructor;
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
}
