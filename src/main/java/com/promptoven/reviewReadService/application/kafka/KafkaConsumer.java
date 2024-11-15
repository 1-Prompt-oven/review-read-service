package com.promptoven.reviewReadService.application.kafka;

import com.promptoven.reviewReadService.document.ReviewReadDocument;
import com.promptoven.reviewReadService.dto.in.Message.CreateRequestMessageDto;
import com.promptoven.reviewReadService.dto.in.Message.DeleteRequestMessageDto;
import com.promptoven.reviewReadService.dto.in.Message.UpdateRequestMessageDto;
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

    private final MongoReviewRepository mongoReviewRepository;
    private static final String CREATE_TOPIC = "create_review_event"; // 각각 이벤트의 발행
    private static final String UPDATE_TOPIC = "update_review_event";
    private static final String DELETE_TOPIC = "delete_review_event";
    private static final String GROUP_ID = "kafka-review-read-service";

    @KafkaListener(topics = CREATE_TOPIC, groupId = GROUP_ID, containerFactory = "createKafkaListenerContainerFactory")
    public void consumeCreate(CreateRequestMessageDto message) {

        log.info("Consumed message: {}", message);

        mongoReviewRepository.save(CreateRequestMessageDto.toCreateDocument(message));
    }

    @KafkaListener(topics = UPDATE_TOPIC, groupId = GROUP_ID, containerFactory = "updateKafkaListenerContainerFactory")
    public void consumeUpdate(UpdateRequestMessageDto message) {

        ReviewReadDocument reviewReadDocument = mongoReviewRepository.findByReviewId(message.getReviewId());

        mongoReviewRepository.save(ReviewSaveDto.toUpdateDocument(reviewReadDocument, message));

    }

    @KafkaListener(topics = DELETE_TOPIC, groupId = GROUP_ID, containerFactory = "deleteKafkaListenerContainerFactory")
    public void consumeDelete(DeleteRequestMessageDto message) {

        ReviewReadDocument reviewReadDocument = mongoReviewRepository.findByReviewId(message.getReviewId());

        mongoReviewRepository.save(ReviewSaveDto.toDeleteDocument(reviewReadDocument));
    }
}
