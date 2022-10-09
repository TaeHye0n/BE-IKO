package com.iko.iko.service.coupon;

import com.iko.iko.domain.entity.Coupon;
import com.iko.iko.domain.repository.coupon.CouponRepository;
import com.iko.iko.controller.coupon.dto.request.CouponRequestDto.InsertCouponRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class InsertCouponService {

    private final CouponRepository couponRepository;

    @Transactional
    public Integer insertCoupon(InsertCouponRequest insertCouponRequest){
        Coupon coupon = insertCouponRequest.toEntity();
        Coupon newCoupon = couponRepository.save(coupon);
        return newCoupon.getCouponId();
    }
}
