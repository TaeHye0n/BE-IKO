package com.iko.iko.controller.coupon.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CouponRequestDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class AddCouponRequest{
        private Integer couponId;
    }
}
