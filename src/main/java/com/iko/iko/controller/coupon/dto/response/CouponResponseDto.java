package com.iko.iko.controller.coupon.dto.response;

import lombok.*;

import java.util.Date;


public class CouponResponseDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CouponInfoListResponse {
        private Integer couponId;
        private Integer status;
        private Date expiredDate;
        private Float discountRate;
        private Integer discount;
        private Integer minPrice;
        private Integer couponType;
        private String couponTitle;
    }
}
