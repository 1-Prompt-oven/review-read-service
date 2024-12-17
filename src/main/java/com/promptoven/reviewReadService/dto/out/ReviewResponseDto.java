package com.promptoven.reviewReadService.dto.out;

import com.promptoven.reviewReadService.document.ReviewReadDocument;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewResponseDto {

    private Long id;
    private String productUuid;
    private String contents;
    private int star;
    private String authorUuid;
    private String authorProfileImage;
    private String authorNickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public ReviewResponseDto(Long id, String productUuid, String contents, int star, String authorUuid,
            String authorProfileImage,
            String authorNickname, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.productUuid = productUuid;
        this.contents = contents;
        this.star = star;
        this.authorUuid = authorUuid;
        this.authorProfileImage = authorProfileImage;
        this.authorNickname = authorNickname;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ReviewResponseDto toDto(ReviewReadDocument reviewReadDocument) {
        return ReviewResponseDto.builder()
                .id(reviewReadDocument.getReviewId())
                .productUuid(reviewReadDocument.getProductUuid())
                .contents(reviewReadDocument.getContents())
                .star(reviewReadDocument.getStar())
                .authorUuid(reviewReadDocument.getAuthorUuid())
                .authorProfileImage(reviewReadDocument.getAuthorProfileImage())
                .authorNickname(reviewReadDocument.getAuthorNickname())
                .createdAt(reviewReadDocument.getCreatedAt())
                .updatedAt(reviewReadDocument.getUpdatedAt())
                .build();
    }
}
