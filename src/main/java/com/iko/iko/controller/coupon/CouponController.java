package com.iko.iko.controller.coupon;

import com.iko.iko.common.response.Response;
import com.iko.iko.service.coupon.facade.CouponFacade;
import com.iko.iko.controller.coupon.dto.request.CouponRequestDto.*;
import com.iko.iko.controller.coupon.dto.response.CouponResponseDto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/coupon")
public class CouponController {

    private final CouponFacade couponFacade;

    @PostMapping("/add")
    public ResponseEntity<Response<String>> addCoupon(
            @RequestBody @Valid AddCouponRequest addCouponRequest) {
        return ResponseEntity.ok(
                Response.of(couponFacade.addCoupon(addCouponRequest),
                        "쿠폰 발급 완료")
        );
    }

    @GetMapping("/list")
    public ResponseEntity<Response<List<CouponInfoListResponse>>> myCoupon(){
        return ResponseEntity.ok(
                Response.of(couponFacade.myCoupon(),
                        "쿠폰 조회 완료")
        );
    }
}
