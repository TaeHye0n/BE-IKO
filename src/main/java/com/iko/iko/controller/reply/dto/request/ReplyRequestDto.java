package com.iko.iko.controller.reply.dto.request;

import com.iko.iko.domain.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ReplyRequestDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class AddReplyRequest {

        private Integer memberId;
        private Integer orderId;
        private Integer productDetailsId;
        private String content;
        private Float rating;
        private String replyImageUrl;

        @Builder
        public Reply toEntity(){
            return Reply.builder()
                    .memberId(memberId)
                    .orderId(orderId)
                    .productDetailsId(productDetailsId)
                    .content(content)
                    .rating(rating)
                    .imageUrl(replyImageUrl)
                    .build();
        }
    }

}
