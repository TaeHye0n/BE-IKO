package com.iko.iko.domain.repository.linkMemberCoupon;

import com.iko.iko.domain.entity.LinkMemberCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkMemberCouponRepository extends JpaRepository<LinkMemberCoupon, Integer>, LinkMemberCouponRepositoryCustom {
}
