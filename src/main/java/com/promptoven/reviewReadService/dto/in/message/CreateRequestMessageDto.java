package com.promptoven.reviewReadService.dto.in.message;

import com.promptoven.reviewReadService.document.ReviewReadDocument;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class CreateRequestMessageDto {

    private Long reviewId;
    private String productUuid;
    private String contents;
    private int star;
    private String authorProfileImage;
    private String authorUuid;
    private String authorNickname;

    @Builder
    public CreateRequestMessageDto(Long reviewId, String productUuid, String authorUuid, String authorProfileImage,
            String authorNickname, String contents, int star) {
        this.reviewId = reviewId;
        this.productUuid = productUuid;
        this.authorUuid = authorUuid;
        this.authorProfileImage = authorProfileImage;
        this.authorNickname = authorNickname;
        this.contents = contents;
        this.star = star;
    }

    public static ReviewReadDocument toCreateDocument(CreateRequestMessageDto createRequestMessageDto) {
        return ReviewReadDocument.builder()
                .reviewId(createRequestMessageDto.getReviewId())
                .productUuid(createRequestMessageDto.getProductUuid())
                .contents(createRequestMessageDto.getContents())
                .star(createRequestMessageDto.getStar())
                .authorUuid(createRequestMessageDto.getAuthorUuid())
                .authorProfileImage(createRequestMessageDto.getAuthorProfileImage())
                .authorNickname(createRequestMessageDto.getAuthorNickname())
                .isDeleted(false)
                .build();
    }
}
