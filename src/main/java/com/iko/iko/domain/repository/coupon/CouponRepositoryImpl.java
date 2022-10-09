package com.iko.iko.domain.repository.coupon;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.iko.iko.controller.coupon.dto.response.CouponResponseDto.*;


import java.util.List;

import static com.iko.iko.domain.entity.QCoupon.coupon;
import static com.iko.iko.domain.entity.QLinkMemberCoupon.linkMemberCoupon;


@Repository
@RequiredArgsConstructor
public class CouponRepositoryImpl implements CouponRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CouponInfoListResponse> getMyCouponInfo(
            Integer memberId
    ) {
        return jpaQueryFactory.select(Projections.constructor(CouponInfoListResponse.class,
                linkMemberCoupon.couponId,
                linkMemberCoupon.status,
                linkMemberCoupon.expiredDate,
                coupon.discountRate,
                coupon.discount,
                coupon.minPrice,
                coupon.couponType,
                coupon.couponTitle
                ))
                .from(linkMemberCoupon)
                .join(coupon).on(linkMemberCoupon.couponId.eq(coupon.couponId)).fetchJoin()
                .where(linkMemberCoupon.memberId.eq(memberId))
                .fetch();
    }


}
