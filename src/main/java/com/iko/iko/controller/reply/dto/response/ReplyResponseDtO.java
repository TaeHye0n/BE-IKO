package com.iko.iko.controller.reply.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

public class ReplyResponseDtO {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MyReplyInfoResponse{
        private MyReplyAndIdsResponse replyInfo;
        private List<ProductInfoForReplyResponse> productInfo;
    }
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MyReplyAndIdsResponse {
        private Integer replyId;
        private Date replyCreatedAt;
        private Integer orderId;
        private String replyImageUrl;
        private Float replyRating;
        private String replyComment;
        private Integer productDetailsId;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ProductInfoForReplyResponse {
        private String productName;
        private String color;
        private Float graphicDiameter;
        private Float degree;
        private Integer pcs;
        private Integer period;
        private String productImageUrl;
    }
}
