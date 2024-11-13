package com.promptoven.reviewReadService.dto.in;

import com.promptoven.reviewReadService.document.ReviewReadDocument;
import com.promptoven.reviewReadService.global.common.response.BaseResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class ReviewSaveDto {

    private String productUuid;
    private String memberUuid;
    private String contents;
    private String memberProfileImage;
    private String memberNickname;
    private Boolean isDeleted;

    @Builder
    public ReviewSaveDto(String productUuid, String memberUuid, String contents, String memberProfileImage,
            String memberNickname) {
        this.productUuid = productUuid;
        this.memberUuid = memberUuid;
        this.contents = contents;
        this.memberProfileImage = memberProfileImage;
        this.memberNickname = memberNickname;
        this.isDeleted = false;
    }

    public static ReviewSaveDto toSaveDto(RequestMessageDto message, BaseResponse<MemberProfileDto> memberProfileDto) {
        return ReviewSaveDto.builder()
                .productUuid(message.getProductUuid())
                .memberUuid(message.getMemberUuid())
                .contents(message.getContents())
                .memberNickname(memberProfileDto.result().getMemberNickname())
                .memberProfileImage(memberProfileDto.result().getMemberProfileImage())
                .build();
    }

    public ReviewReadDocument toDocument(ReviewSaveDto reviewSaveDto) {
        return ReviewReadDocument.builder()
                .productUuid(reviewSaveDto.getProductUuid())
                .memberUuid(reviewSaveDto.getMemberUuid())
                .contents(reviewSaveDto.getContents())
                .memberProfileImage(reviewSaveDto.getMemberProfileImage())
                .memberNickname(reviewSaveDto.getMemberNickname())
                .isDeleted(reviewSaveDto.getIsDeleted())
                .build();
    }
}
