package com.iko.iko.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name="tb_product_details_image")
@DynamicInsert
@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinkProductDetailsImage extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="link_id_pk",unique = true, nullable = false)
    private Integer linkId;

    @Column(name="product_details_id_fk",nullable = false)
    private Integer productDetailsId;

    @Column(name="image_id_fk",nullable = false)
    private Integer imageId;
}
