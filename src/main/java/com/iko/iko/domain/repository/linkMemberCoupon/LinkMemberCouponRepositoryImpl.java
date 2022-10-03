package com.iko.iko.domain.repository.linkMemberCoupon;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.iko.iko.domain.entity.QLinkMemberCoupon.linkMemberCoupon;

@Repository
@RequiredArgsConstructor
public class LinkMemberCouponRepositoryImpl implements LinkMemberCouponRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Long setStatusUsed(
            Integer memberId, Integer couponId
    ){
        return jpaQueryFactory
                .update(linkMemberCoupon)
                .set(linkMemberCoupon.status, 2)
                .where(linkMemberCoupon.couponId.eq(couponId)
                        .and(linkMemberCoupon.memberId.eq(memberId)))
                .execute();
    }

    @Override
    public Long setStatusAvailable(
            Integer memberId, Integer couponId
    ){
        return jpaQueryFactory
                .update(linkMemberCoupon)
                .set(linkMemberCoupon.status, 1)
                .where(linkMemberCoupon.couponId.eq(couponId)
                        .and(linkMemberCoupon.memberId.eq(memberId)))
                .execute();
    }

}
