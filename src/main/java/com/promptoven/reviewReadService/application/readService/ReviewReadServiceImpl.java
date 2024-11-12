package com.promptoven.reviewReadService.application.readService;

import com.promptoven.reviewReadService.document.ReviewReadDocument;
import com.promptoven.reviewReadService.infrastructure.MongoReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewReadServiceImpl implements ReviewReadService{

    private final MongoReviewRepository mongoReviewRepository;

    @Override
    public void testRequest() {
        mongoReviewRepository.save(new ReviewReadDocument("1", "1", "1", "1", "1"));
    }
}
