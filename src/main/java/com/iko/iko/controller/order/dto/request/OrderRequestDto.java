package com.iko.iko.controller.order.dto.request;

import com.iko.iko.domain.entity.LinkOrderDetails;
import com.iko.iko.domain.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class OrderRequestDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class AddOrderRequest {
        private Integer memberId;
        private String orderer;
        private String ordererPhone;
        private String ordererEmail;
        private String receiver;
        private String address;
        private String detailAddress;
        private String receiverPhone;
        private String shippingMessage;
        private List<AddOrderDetailsRequest> addOrderDetailsRequestList;
        private Integer couponId;
        private String method;
        private Integer totalPrice;
        private Integer point;

        @Getter
        @Setter
        @Builder
        @AllArgsConstructor
        public static class AddOrderDetailsRequest {

            private Integer productDetailsId;
            private Integer set;

            @Builder
            public LinkOrderDetails toEntity() {
                return LinkOrderDetails.builder()
                        .productDetailsId(productDetailsId)
                        .set(set)
                        .build();
            }
        }

        @Builder
        public Order toEntity() {
            return Order.builder()
                    .memberId(memberId)
                    .name(orderer)
                    .phone(ordererPhone)
                    .email(ordererEmail)
                    .receiverName(receiver)
                    .destination(address)
                    .detailDestination(detailAddress)
                    .receiverPhone(receiverPhone)
                    .message(shippingMessage)
                    .couponId(couponId)
                    .method(method)
                    .totalPrice(totalPrice)
                    .point(point)
                    .build();
        }
    }

}
