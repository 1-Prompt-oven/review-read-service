package com.promptoven.reviewReadService.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class RequestMessageDto {

    private String productUuid;
    private String contents;
    private String memberUuid;

    @Builder
    public RequestMessageDto(String productUuid, String contents, String memberUuid) {
        this.productUuid = productUuid;
        this.contents = contents;
        this.memberUuid = memberUuid;
    }
}
