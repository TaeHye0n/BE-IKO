package com.iko.iko.domain.repository.linkMemberCoupon;

import com.iko.iko.domain.entity.LinkMemberCoupon;

import java.util.List;

public interface LinkMemberCouponRepositoryCustom {

    Long setStatusUsed(Integer memberId, Integer couponId);

    Long setStatusAvailable(Integer memberId, Integer couponId);

    List<LinkMemberCoupon> getLinkMemberCouponList(Integer memberId, Integer couponId);


}
