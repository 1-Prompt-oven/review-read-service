package com.promptoven.reviewReadService.dto.in;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReviewRequestDto {

    private String productUuid;
    private LocalDateTime lastCreatedAt;
    private String lastId;
    private Integer pageSize;
    private Integer page;

    @Builder
    public ReviewRequestDto(String productUuid, LocalDateTime lastCreatedAt, String lastId, Integer pageSize,
            Integer page) {
        this.productUuid = productUuid;
        this.lastCreatedAt = lastCreatedAt;
        this.lastId = lastId;
        this.pageSize = pageSize;
        this.page = page;
    }

    public static ReviewRequestDto toPaginationDto(String productUuid, LocalDateTime lastCreatedAt, String lastId,
            Integer pageSize, Integer page) {
        return ReviewRequestDto.builder()
                .productUuid(productUuid)
                .lastCreatedAt(lastCreatedAt)
                .lastId(lastId)
                .pageSize(pageSize)
                .page(page)
                .build();
    }
}
