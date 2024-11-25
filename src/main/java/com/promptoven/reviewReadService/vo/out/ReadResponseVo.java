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
    private String authorUuid;
    private String authorProfileImage;
    private String authorNickname;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public ReadResponseVo(String id, String productUuid, String contents, int star, String authorUuid,
            String authorProfileImage, String authorNickname, LocalDateTime createdAt, LocalDateTime updatedAt) {
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

    public static List<ReadResponseVo> toVoList(List<ReviewResponseDto> reviewResponseDtoList) {
        return reviewResponseDtoList.stream()
                .map(dto -> ReadResponseVo.builder()
                        .id(dto.getId())
                        .productUuid(dto.getProductUuid())
                        .contents(dto.getContents())
                        .star(dto.getStar())
                        .authorUuid(dto.getAuthorUuid())
                        .authorProfileImage(dto.getAuthorProfileImage())
                        .authorNickname(dto.getAuthorNickname())
                        .createdAt(dto.getCreatedAt())
                        .updatedAt(dto.getUpdatedAt())
                        .build())
                .toList();
    }
}
