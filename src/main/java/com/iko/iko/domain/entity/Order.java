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
@Table(name = "tb_order")
@DynamicInsert
@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id_pk", unique = true, nullable = false)
    private Integer orderId;

    @Column(name = "member_id_fk")
    private Integer memberId;

    @Column(name = "product_details_id_fk")
    private Integer productDetailsId;

    @Column(name = "order_status")
    private Integer orderStatus;

    @Column(name = "destination")
    private String destination;

    @Column(name = "order_price")
    private Integer orderPrice;

}
