package com.promptoven.reviewReadService.dto.in.Message;

import com.promptoven.reviewReadService.document.ReviewReadDocument;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class DeleteRequestMessageDto {
    private Long reviewId;

    @Builder
    public DeleteRequestMessageDto(Long reviewId) {
        this.reviewId = reviewId;
    }

}
