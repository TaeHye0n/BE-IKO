package com.iko.iko.service.coupon.facade;

import com.iko.iko.controller.coupon.dto.request.CouponRequestDto.*;
import com.iko.iko.controller.coupon.dto.response.CouponResponseDto.*;
import com.iko.iko.controller.coupon.dto.request.CouponRequestDto.InsertCouponRequest;
import com.iko.iko.service.coupon.AddCouponService;
import com.iko.iko.service.coupon.InsertCouponService;
import com.iko.iko.service.coupon.MyCouponListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CouponFacade {

    private final AddCouponService addCouponService;
    private final MyCouponListService myCouponListService;
    private final InsertCouponService insertCouponService;
    @Transactional
    public String addCoupon(AddCouponRequest addCouponRequest){
        return addCouponService.addCoupon(addCouponRequest);
    }

    @Transactional
    public List<CouponInfoListResponse> myCoupon(){
        return myCouponListService.myCoupon();
    }

    @Transactional
    public Integer insertCoupon(InsertCouponRequest insertCouponRequest){
        return insertCouponService.insertCoupon(insertCouponRequest);
    }
}
