package com.iko.iko.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name="tb_favor_product")
@DynamicInsert
@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkFavorProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="favor_product_id_pk",unique = true, nullable = false)
    private Integer linkId;

    @Column(name="product_id_fk",nullable = false)
    private Integer productId;

    @Column(name="favor_id_fk",nullable = false)
    private Integer favorId;

}