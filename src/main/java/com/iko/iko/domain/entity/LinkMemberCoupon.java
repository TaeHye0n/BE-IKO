package com.iko.iko.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "tb_member_coupon")
@DynamicInsert
@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class LinkMemberCoupon extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_coupon_id_pk",unique = true, nullable = false)
    private Integer memberCouponId;

    @Column(name="coupon_id_fk",nullable = false)
    private Integer couponId;

    @Column(name="member_id_fk",nullable = false)
    private Integer memberId;

    @Column(name="status", nullable = false)
    private Integer status;

}
