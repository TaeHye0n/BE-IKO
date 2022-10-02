package com.iko.iko.domain.repository.coupon;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.iko.iko.domain.entity.QCoupon.coupon;


@Repository
@RequiredArgsConstructor
public class CouponRepositoryImpl implements CouponRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

}
