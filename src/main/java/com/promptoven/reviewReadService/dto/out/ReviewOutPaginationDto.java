package com.promptoven.reviewReadService.dto.out;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewOutPaginationDto {
    private List<ReviewResponseDto> reviewResponseDtoList;
    private LocalDateTime lastCreatedAt;
    private Boolean hasNext;
    private Long lastId;
    private Integer pageSize;
    private Integer page;

    @Builder
    public ReviewOutPaginationDto(List<ReviewResponseDto> reviewResponseDtoList, LocalDateTime lastCreatedAt, Boolean hasNext,
            Long lastId, Integer pageSize, Integer page) {
        this.reviewResponseDtoList = reviewResponseDtoList;
        this.lastCreatedAt = lastCreatedAt;
        this.hasNext = hasNext;
        this.lastId = lastId;
        this.pageSize = pageSize;
        this.page = page;
    }
}
