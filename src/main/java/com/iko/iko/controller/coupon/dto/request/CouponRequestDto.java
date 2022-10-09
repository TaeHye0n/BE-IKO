package com.iko.iko.controller.coupon.dto.request;

import com.iko.iko.domain.entity.Coupon;
import com.iko.iko.domain.entity.LinkMemberCoupon;
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

        @Builder
        public LinkMemberCoupon toEntity(){
            return LinkMemberCoupon.builder()
                    .couponId(couponId)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class InsertCouponRequest {
        private Integer expirationPeriod;
        private Float discountRate;
        private Integer discount;
        private Integer minPrice;
        private Integer couponType;
        private String couponTitle;

        @Builder
        public Coupon toEntity(){
            return Coupon.builder()
                    .expirationPeriod(expirationPeriod)
                    .discountRate(discountRate)
                    .discount(discount)
                    .minPrice(minPrice)
                    .couponType(couponType)
                    .couponTitle(couponTitle)
                    .build();
        }
    }
}
