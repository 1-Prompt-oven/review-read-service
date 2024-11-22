package com.promptoven.reviewReadService.dto.in;

import com.promptoven.reviewReadService.document.ReviewReadDocument;
import com.promptoven.reviewReadService.dto.in.message.UpdateRequestMessageDto;
import java.time.LocalDateTime;
import java.util.List;
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
    private String authorUuid;
    private String contents;
    private int star;
    private String authorProfileImage;
    private String authorNickname;
    private Boolean isDeleted;

    @Builder
    public ReviewSaveDto(String id, Long reviewId, String productUuid, String authorUuid, String contents, int star,
            String authorProfileImage, String authorNickname) {
        this.id = id;
        this.reviewId = reviewId;
        this.productUuid = productUuid;
        this.authorUuid = authorUuid;
        this.contents = contents;
        this.star = star;
        this.authorProfileImage = authorProfileImage;
        this.authorNickname = authorNickname;
        this.isDeleted = false;
    }

    public static ReviewReadDocument toUpdateDocument(ReviewReadDocument reviewReadDocument, UpdateRequestMessageDto message) {
        return ReviewReadDocument.builder()
                .id(reviewReadDocument.getId())
                .reviewId(reviewReadDocument.getReviewId())
                .productUuid(reviewReadDocument.getProductUuid())
                .authorUuid(reviewReadDocument.getAuthorUuid())
                .contents(message.getContents())
                .star(message.getStar())
                .authorProfileImage(reviewReadDocument.getAuthorProfileImage())
                .authorNickname(reviewReadDocument.getAuthorNickname())
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
                .authorUuid(reviewReadDocument.getAuthorUuid())
                .contents(reviewReadDocument.getContents())
                .star(reviewReadDocument.getStar())
                .authorProfileImage(reviewReadDocument.getAuthorProfileImage())
                .authorNickname(reviewReadDocument.getAuthorNickname())
                .createdAt(reviewReadDocument.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .isDeleted(true)
                .build();
    }

}
