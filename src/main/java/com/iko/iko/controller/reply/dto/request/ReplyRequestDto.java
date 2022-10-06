package com.iko.iko.controller.reply.dto.request;

import com.iko.iko.domain.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReplyRequestDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class AddReplyRequest {

        private Integer memberId;
        private Integer orderId;
        private Integer productDetailsId;
        private String content;
        private Float rating;
        private String replyImageUrl;

        @Builder
        public Reply toEntity() {
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

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class DeleteReplyRequest {
        private Integer replyId;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UpdateReplyRequest {
        private Integer replyId;
        private Float rating;
        private String content;
        private String imageUrl;
    }

}
