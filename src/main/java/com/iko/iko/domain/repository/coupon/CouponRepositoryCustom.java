package com.iko.iko.domain.repository.coupon;

import com.iko.iko.controller.coupon.dto.response.CouponResponseDto.*;

import java.util.List;

public interface CouponRepositoryCustom {

    List<CouponInfoListResponse> getMyCouponInfo(Integer memberId);
}
