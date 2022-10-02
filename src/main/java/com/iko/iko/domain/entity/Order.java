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
public class Order extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id_pk", unique = true, nullable = false)
    private Integer orderId;

    @Column(name = "member_id_fk" , nullable = false)
    private Integer memberId;

    @Column(name = "coupon_id_fk")
    private Integer couponId;

    @Column(name = "status" , nullable = false)
    private Integer status;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "phone" , nullable = false)
    private String phone;

    @Column(name = "email" , nullable = false)
    private String email;

    @Column(name = "total_price" , nullable = false)
    private Integer totalPrice;

    @Column(name = "receiver_name" , nullable = false)
    private String receiverName;

    @Column(name = "receiver_phone" , nullable = false)
    private String receiverPhone;

    @Column(name = "destination" , nullable = false)
    private String destination;

    @Column(name = "detail_destination" , nullable = false)
    private String detailDestination;

    @Column(name = "method" , nullable = false)
    private String method;

    @Column(name = "point")
    private Integer point;

    @Column(name = "message")
    private String message;


}
