package com.iko.iko.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "tb_coupon")
@DynamicInsert
@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Coupon extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id_pk", unique = true, nullable = false)
    private Integer couponId;

    @Column(name = "expiration_period", updatable = false)
    private Integer expirationPeriod;

    @Column(name = "discount_rate")
    private Float discountRate;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "min_price" , nullable = false)
    private Integer minPrice;

    @Column(name = "coupon_type" , nullable = false)
    private Integer couponType;

    @Column(name = "coupon_title" , nullable = false)
    private String couponTitle;
}
