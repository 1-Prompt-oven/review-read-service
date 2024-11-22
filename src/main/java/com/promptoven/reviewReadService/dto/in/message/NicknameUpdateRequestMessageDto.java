package com.promptoven.reviewReadService.dto.in.message;

import lombok.Getter;

@Getter
public class NicknameUpdateRequestMessageDto {

    private String memberUUID;
    private String nickname;
}
