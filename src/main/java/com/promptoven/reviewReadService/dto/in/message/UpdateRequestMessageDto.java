package com.promptoven.reviewReadService.dto.in.message;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class UpdateRequestMessageDto {

    private Long reviewId;
    private String contents;
    private int star;

    @Builder
    public UpdateRequestMessageDto(Long reviewId, String contents, int star) {
        this.reviewId = reviewId;
        this.contents = contents;
        this.star = star;
    }
}
