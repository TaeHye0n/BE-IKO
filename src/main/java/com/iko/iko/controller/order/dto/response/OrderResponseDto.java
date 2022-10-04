package com.iko.iko.controller.order.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

public class OrderResponseDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class GetOrderResponse{
        private GetOrderInfoResponse OrderInfo;
        private List<GetProductForOrderResponse> ProductInfo;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class GetOrderInfoResponse{
        private Date orderCreatedAt;
        private Integer OrderId;
        private Integer status;
        private Integer totalPrice;
        private Integer method;
        private String orderer;
        private String phone;
        private String email;
        private String receiver;
        private Integer postCode;
        private String address;
        private String detailAddress;
        private String receiverPhone;
        private String shippingMessage;
        private Integer point;
        private Integer couponId;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class GetProductForOrderResponse {
        private Integer productId;
        private Integer productDetailsId;
        private String productName;
        private String color;
        private String colorCode;
        private Float graphicDiameter;
        private Float degree;
        private Integer pcs;
        private Integer price;
        private Integer period;
        private String imageUrl;

    }

}
