package com.iko.iko.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name="tb_cart_details")
@DynamicInsert
@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkCartFavorDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_details_id_pk",unique = true, nullable = false)
    private Integer linkId;

    @Column(name="cart_favor_id_fk",nullable = false)
    private Integer cartFavorId;

    @Column(name="product_details_id_fk",nullable = false)
    private Integer productDetailsId;
}
