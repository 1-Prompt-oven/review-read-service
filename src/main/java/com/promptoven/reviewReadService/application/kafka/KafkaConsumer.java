package com.promptoven.reviewReadService.application.kafka;

import static com.promptoven.reviewReadService.global.common.response.BaseResponseStatus.NO_EXIST_REVIEW;

import com.promptoven.reviewReadService.document.ReviewReadDocument;
import com.promptoven.reviewReadService.dto.in.ReviewSaveDto;
import com.promptoven.reviewReadService.dto.in.message.CreateRequestMessageDto;
import com.promptoven.reviewReadService.dto.in.message.DeleteRequestMessageDto;
import com.promptoven.reviewReadService.dto.in.message.ImgUpdateRequestMessageDto;
import com.promptoven.reviewReadService.dto.in.message.NicknameUpdateRequestMessageDto;
import com.promptoven.reviewReadService.dto.in.message.UpdateRequestMessageDto;
import com.promptoven.reviewReadService.global.error.BaseException;
import com.promptoven.reviewReadService.infrastructure.MongoReviewRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final MongoReviewRepository mongoReviewRepository;
    private static final String GROUP_ID = "kafka-review-read-service";

    @KafkaListener(topics = "${review-create-event}", groupId = GROUP_ID, containerFactory = "createKafkaListenerContainerFactory")
    public void consumeCreate(CreateRequestMessageDto message) {

        log.info("Consumed message: {}", message);

        mongoReviewRepository.save(CreateRequestMessageDto.toCreateDocument(message));
    }

    @KafkaListener(topics = "${review-update-event}", groupId = GROUP_ID, containerFactory = "updateKafkaListenerContainerFactory")
    public void consumeUpdate(UpdateRequestMessageDto message) {

        ReviewReadDocument reviewReadDocument = mongoReviewRepository.findByReviewId(message.getReviewId())
                .orElseThrow(() -> new BaseException(NO_EXIST_REVIEW));

        mongoReviewRepository.save(ReviewSaveDto.toUpdateDocument(reviewReadDocument, message));

    }

    @KafkaListener(topics = "${review-delete-event}", groupId = GROUP_ID, containerFactory = "deleteKafkaListenerContainerFactory")
    public void consumeDelete(DeleteRequestMessageDto message) {

        ReviewReadDocument reviewReadDocument = mongoReviewRepository.findByReviewId(message.getReviewId())
                .orElseThrow(() -> new BaseException(NO_EXIST_REVIEW));

        mongoReviewRepository.save(ReviewSaveDto.toDeleteDocument(reviewReadDocument));
    }

    @KafkaListener(topics = "${member-nickname-update-event}", groupId = GROUP_ID, containerFactory = "nicknameUpdateKafkaListenerContainerFactory")
    public void consumeNicknameUpdate(NicknameUpdateRequestMessageDto message) {

        List<ReviewReadDocument> reviewReadDocumentList = mongoReviewRepository.findByAuthorUuid(
                message.getMemberUUID());

        mongoReviewRepository.saveAll(updateReviewField(reviewReadDocumentList, message.getNickname(), "nickname"));
    }

    @KafkaListener(topics = "${profile-picture-update-event}", groupId = GROUP_ID, containerFactory = "profileImgUpdateKafkaListenerContainerFactory")
    public void consumeProfileImgUpdate(ImgUpdateRequestMessageDto message) {
        List<ReviewReadDocument> reviewReadDocumentList = mongoReviewRepository.findByAuthorUuid(
                message.getMemberUUID());

        mongoReviewRepository.saveAll(updateReviewField(reviewReadDocumentList, message.getPicture(), "profileImage"));
    }


    private List<ReviewReadDocument> updateReviewField(

            List<ReviewReadDocument> reviewReadDocumentList, String newData, String fieldType) {

        return reviewReadDocumentList.stream()
                .map(review -> {
                    ReviewReadDocument.ReviewReadDocumentBuilder builder = ReviewReadDocument.builder()
                            .id(review.getId())
                            .reviewId(review.getReviewId())
                            .productUuid(review.getProductUuid())
                            .authorUuid(review.getAuthorUuid())
                            .contents(review.getContents())
                            .star(review.getStar())
                            .isDeleted(review.getIsDeleted())
                            .createdAt(review.getCreatedAt())
                            .updatedAt(LocalDateTime.now());

                    if (fieldType.equals("nickname")) {
                        builder.authorNickname(newData);
                    } else if (fieldType.equals("profileImage")) {
                        builder.authorProfileImage(newData);
                    }

                    return builder.build();
                })
                .toList();
    }
}
