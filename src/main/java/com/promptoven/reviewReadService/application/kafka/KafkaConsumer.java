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
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final MongoReviewRepository mongoReviewRepository;
    private static final String CREATE_TOPIC = "create_review_event"; // 각각 이벤트의 발행
    private static final String UPDATE_TOPIC = "update_review_event";
    private static final String DELETE_TOPIC = "delete_review_event";
    private static final String NICKNAME_UPDATE_TOPIC = "member-nickname-updated";
    private static final String PROFILE_IMG_UPDATE_TOPIC = "profile-picture-updated";
    private static final String GROUP_ID = "kafka-review-read-service";

    @KafkaListener(topics = CREATE_TOPIC, groupId = GROUP_ID, containerFactory = "createKafkaListenerContainerFactory")
    public void consumeCreate(CreateRequestMessageDto message) {

        mongoReviewRepository.save(CreateRequestMessageDto.toCreateDocument(message));
    }

    @KafkaListener(topics = UPDATE_TOPIC, groupId = GROUP_ID, containerFactory = "updateKafkaListenerContainerFactory")
    public void consumeUpdate(UpdateRequestMessageDto message) {

        ReviewReadDocument reviewReadDocument = mongoReviewRepository.findByReviewId(message.getReviewId())
                .orElseThrow(() -> new BaseException(NO_EXIST_REVIEW));

        mongoReviewRepository.save(ReviewSaveDto.toUpdateDocument(reviewReadDocument, message));

    }

    @KafkaListener(topics = DELETE_TOPIC, groupId = GROUP_ID, containerFactory = "deleteKafkaListenerContainerFactory")
    public void consumeDelete(DeleteRequestMessageDto message) {

        ReviewReadDocument reviewReadDocument = mongoReviewRepository.findByReviewId(message.getReviewId())
                .orElseThrow(() -> new BaseException(NO_EXIST_REVIEW));

        mongoReviewRepository.save(ReviewSaveDto.toDeleteDocument(reviewReadDocument));
    }

    @KafkaListener(topics = NICKNAME_UPDATE_TOPIC, groupId = GROUP_ID, containerFactory = "nicknameUpdateKafkaListenerContainerFactory")
    public void consumeNicknameUpdate(NicknameUpdateRequestMessageDto message) {

        List<ReviewReadDocument> reviewReadDocumentList = mongoReviewRepository.findByAuthorUuid(
                message.getMemberUUID());

        mongoReviewRepository.saveAll(updateReviewField(reviewReadDocumentList, message.getNickname(), "nickname"));
    }

    @KafkaListener(topics = PROFILE_IMG_UPDATE_TOPIC, groupId = GROUP_ID, containerFactory = "profileImgUpdateKafkaListenerContainerFactory")
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
