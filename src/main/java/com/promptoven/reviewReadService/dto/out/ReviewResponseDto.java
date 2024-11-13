package com.promptoven.reviewReadService.dto.out;

import com.promptoven.reviewReadService.document.ReviewReadDocument;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewResponseDto {

    private String id;
    private String productUuid;
    private String contents;
    private String memberUuid;
    private String memberProfileImage;
    private String memberNickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public ReviewResponseDto(String id, String productUuid, String contents, String memberUuid, String memberProfileImage,
            String memberNickname, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.productUuid = productUuid;
        this.contents = contents;
        this.memberUuid = memberUuid;
        this.memberProfileImage = memberProfileImage;
        this.memberNickname = memberNickname;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

//    public static List<ReviewResponseDto> toDtoList(List<ReviewReadDocument> reviewReadDocumentList) {
//        return reviewReadDocumentList.stream()
//                .map(document -> ReviewResponseDto.builder()
//                        .id(document.getId())
//                        .productUuid(document.getProductUuid())
//                        .contents(document.getContents())
//                        .memberUuid(document.getMemberUuid())
//                        .memberProfileImage(document.getMemberProfileImage())
//                        .memberNickname(document.getMemberNickname())
//                        .createdAt(document.getCreatedAt())
//                        .updatedAt(document.getUpdatedAt())
//                        .build())
//                .toList();
//    }

    public static ReviewResponseDto toDto(ReviewReadDocument reviewReadDocument) {
        return ReviewResponseDto.builder()
                .id(reviewReadDocument.getId())
                .productUuid(reviewReadDocument.getProductUuid())
                .contents(reviewReadDocument.getContents())
                .memberUuid(reviewReadDocument.getMemberUuid())
                .memberProfileImage(reviewReadDocument.getMemberProfileImage())
                .memberNickname(reviewReadDocument.getMemberNickname())
                .createdAt(reviewReadDocument.getCreatedAt())
                .updatedAt(reviewReadDocument.getUpdatedAt())
                .build();
    }
}
