package com.iko.iko.domain.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "tb_cart")
@DynamicInsert
@DynamicUpdate
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id_pk", unique = true, nullable = false)
    private Integer cartId;

    @Column(name = "member_id_fk" , nullable = false)
    private Integer memberId;

    @Column(name="product_details_id_fk",nullable = false)
    private Integer productDetailsId;

    @Column(name="pcs", nullable = false)
    private Integer pcs;

}
