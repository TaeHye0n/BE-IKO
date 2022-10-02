package com.iko.iko.domain.repository.coupon;

import com.iko.iko.domain.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Integer>, CouponRepositoryCustom  {
}
