package com.promptoven.reviewReadService.dto.in.message;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteRequestMessageDto {

    private Long reviewId;

    @Builder
    public DeleteRequestMessageDto(Long reviewId) {
        this.reviewId = reviewId;
    }

}
