package com.iko.iko.domain.repository.linkMemberCoupon;

import com.iko.iko.domain.entity.LinkMemberCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LinkMemberCouponRepository extends JpaRepository<LinkMemberCoupon, Integer>, LinkMemberCouponRepositoryCustom {
}
