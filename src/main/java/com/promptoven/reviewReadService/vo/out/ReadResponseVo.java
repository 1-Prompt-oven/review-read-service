package com.promptoven.reviewReadService.vo.out;

import com.promptoven.reviewReadService.dto.out.ReviewResponseDto;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReadResponseVo {

    private String id;
    private String productUuid;
    private String contents;
    private int star;
    private String memberUuid;
    private String memberProfileImage;
    private String memberNickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public ReadResponseVo(String id, String productUuid, String contents, int star, String memberUuid, String memberProfileImage,
            String memberNickname, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.productUuid = productUuid;
        this.contents = contents;
        this.star = star;
        this.memberUuid = memberUuid;
        this.memberProfileImage = memberProfileImage;
        this.memberNickname = memberNickname;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static List<ReadResponseVo> toVoList(List<ReviewResponseDto> reviewResponseDtoList) {
        return reviewResponseDtoList.stream()
                .map(dto -> ReadResponseVo.builder()
                        .id(dto.getId())
                        .productUuid(dto.getProductUuid())
                        .contents(dto.getContents())
                        .star(dto.getStar())
                        .memberUuid(dto.getMemberUuid())
                        .memberProfileImage(dto.getMemberProfileImage())
                        .memberNickname(dto.getMemberNickname())
                        .createdAt(dto.getCreatedAt())
                        .updatedAt(dto.getUpdatedAt())
                        .build())
                .toList();
    }
}
