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
    private String memberProfileImage;
    private String memberUuid;
    private String memberNickname;

    @Builder
    public CreateRequestMessageDto(Long reviewId, String productUuid, String contents, int star, String memberUuid,
            String memberProfileImage,String memberNickname) {
        this.reviewId = reviewId;
        this.productUuid = productUuid;
        this.contents = contents;
        this.star = star;
        this.memberProfileImage = memberProfileImage;
        this.memberUuid = memberUuid;
        this.memberNickname = memberNickname;
    }

    public static ReviewReadDocument toCreateDocument(CreateRequestMessageDto createRequestMessageDto) {
        return ReviewReadDocument.builder()
                .reviewId(createRequestMessageDto.getReviewId())
                .productUuid(createRequestMessageDto.getProductUuid())
                .contents(createRequestMessageDto.getContents())
                .star(createRequestMessageDto.getStar())
                .memberUuid(createRequestMessageDto.getMemberUuid())
                .memberProfileImage(createRequestMessageDto.getMemberProfileImage())
                .memberNickname(createRequestMessageDto.getMemberNickname())
                .isDeleted(false)
                .build();
    }
}
