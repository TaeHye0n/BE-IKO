package com.iko.iko.controller.reply.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ReplyRequestDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class AddReply{

        private Integer memberId;
        private Integer orderId;
        private Integer productDetailsId;
    }
}
