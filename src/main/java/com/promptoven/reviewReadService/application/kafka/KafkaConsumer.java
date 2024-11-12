package com.promptoven.reviewReadService.application.kafka;

import com.promptoven.reviewReadService.application.feign.MemberServiceClient;
import com.promptoven.reviewReadService.dto.in.MemberProfileDto;
import com.promptoven.reviewReadService.dto.in.RequestMessageDto;
import com.promptoven.reviewReadService.dto.in.ReviewSaveDto;
import com.promptoven.reviewReadService.infrastructure.MongoReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final MemberServiceClient memberServiceClient;
    private final MongoReviewRepository mongoReviewRepository;
    private static final String CREATE_TOPIC = "create_review_event"; // 각각 이벤트의 발행
    private static final String GROUP_ID = "kafka-review-service";


    @KafkaListener(topics = CREATE_TOPIC, groupId = GROUP_ID)
    public void consumeCreate(RequestMessageDto message) {

//        log.info("Consumed message: {}", message);

        MemberProfileDto memberProfileDto = memberServiceClient.getMemberProfile();

//        log.info("Consumed memberProfileDto: {}", memberProfileDto);

        ReviewSaveDto reviewSaveDto = ReviewSaveDto.toSaveDto(message, memberProfileDto);

        mongoReviewRepository.save(reviewSaveDto.toDocument(reviewSaveDto));
    }
}
