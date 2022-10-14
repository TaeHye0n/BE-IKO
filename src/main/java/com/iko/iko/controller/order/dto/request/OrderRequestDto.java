package com.iko.iko.controller.order.dto.request;

import com.iko.iko.domain.entity.LinkOrderDetails;
import com.iko.iko.domain.entity.Order;
import lombok.*;

import javax.validation.constraints.Email;
import java.util.List;

public class OrderRequestDto {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class AddOrderRequest {
        private Integer memberId;
        private String orderer;
        private String phone;
        private String email;
        private String receiver;
        private Integer postCode;
        private String address;
        private String detailAddress;
        private String receiverPhone;
        private String shippingMessage;
        private List<AddOrderDetailsRequest> products;
        private Integer couponId;
        private Integer method;
        private Integer totalPrice;
        private Float point;

        @Getter
        @Setter
        @Builder
        @AllArgsConstructor
        public static class AddOrderDetailsRequest {

            private Integer productDetailsId;
            private Integer pcs;

            @Builder
            public LinkOrderDetails toEntity() {
                return LinkOrderDetails.builder()
                        .productDetailsId(productDetailsId)
                        .pcs(pcs)
                        .build();
            }
        }

        @Builder
        public Order toEntity() {
            return Order.builder()
                    .memberId(memberId)
                    .name(orderer)
                    .phone(phone)
                    .email(email)
                    .receiverName(receiver)
                    .postCode(postCode)
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

    @Getter
    @Builder
    @AllArgsConstructor
    public static class CancelOrderRequest {

        private Integer orderId;
        private Integer memberId;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class GetOrderRequest {

        private Integer memberId;
        private Integer orderId;
        private String email;
        private String orderer;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UpdateOrderStatusRequest {
        private Integer orderId;
        private Integer status;
    }


}
