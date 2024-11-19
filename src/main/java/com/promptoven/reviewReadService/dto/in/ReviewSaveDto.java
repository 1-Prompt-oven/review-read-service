package com.promptoven.reviewReadService.dto.in;

import com.promptoven.reviewReadService.document.ReviewReadDocument;
import com.promptoven.reviewReadService.dto.in.message.UpdateRequestMessageDto;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class ReviewSaveDto {

    private String id;
    private Long reviewId;
    private String productUuid;
    private String memberUuid;
    private String contents;
    private int star;
    private String memberProfileImage;
    private String memberNickname;
    private Boolean isDeleted;

    @Builder
    public ReviewSaveDto(String id, Long reviewId, String productUuid, String memberUuid, String contents, int star,
            String memberProfileImage, String memberNickname) {
        this.id = id;
        this.reviewId = reviewId;
        this.productUuid = productUuid;
        this.memberUuid = memberUuid;
        this.contents = contents;
        this.star = star;
        this.memberProfileImage = memberProfileImage;
        this.memberNickname = memberNickname;
        this.isDeleted = false;
    }

    public static ReviewReadDocument toUpdateDocument(ReviewReadDocument reviewReadDocument, UpdateRequestMessageDto message) {
        return ReviewReadDocument.builder()
                .id(reviewReadDocument.getId())
                .reviewId(reviewReadDocument.getReviewId())
                .productUuid(reviewReadDocument.getProductUuid())
                .memberUuid(reviewReadDocument.getMemberUuid())
                .contents(message.getContents())
                .star(message.getStar())
                .memberProfileImage(reviewReadDocument.getMemberProfileImage())
                .memberNickname(reviewReadDocument.getMemberNickname())
                .isDeleted(reviewReadDocument.getIsDeleted())
                .createdAt(reviewReadDocument.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static ReviewReadDocument toDeleteDocument(ReviewReadDocument reviewReadDocument) {
        return ReviewReadDocument.builder()
                .id(reviewReadDocument.getId())
                .reviewId(reviewReadDocument.getReviewId())
                .productUuid(reviewReadDocument.getProductUuid())
                .memberUuid(reviewReadDocument.getMemberUuid())
                .contents(reviewReadDocument.getContents())
                .star(reviewReadDocument.getStar())
                .memberProfileImage(reviewReadDocument.getMemberProfileImage())
                .memberNickname(reviewReadDocument.getMemberNickname())
                .createdAt(reviewReadDocument.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .isDeleted(true)
                .build();
    }
}
