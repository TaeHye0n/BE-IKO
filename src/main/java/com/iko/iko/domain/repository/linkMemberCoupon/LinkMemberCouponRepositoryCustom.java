package com.iko.iko.domain.repository.linkMemberCoupon;

public interface LinkMemberCouponRepositoryCustom {

    Long setStatusUsed(Integer memberId, Integer couponId);

    Long setStatusAvailable(Integer memberId, Integer couponId);


}
