package com.iko.iko.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Builder
@Getter
@Table(name = "tb_product_details")
public class ProductDetails extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="product_details_id_pk",unique = true,nullable = false)
    private Integer product_details_id;

    @Column(name="diameter")
    private float diameter;

    @Column(name ="color", nullable = false)
    @NotEmpty
    private String name;

    @Column(name ="period", nullable = false)
    @NotEmpty
    private Integer period;

    @Column(name ="product_details_price", nullable = true)
    @NotEmpty
    private Integer detailsPrice;

    @Column(name="material", nullable = false)
    @NotEmpty
    private String material;

    @Column(name="color_code", nullable = false)
    @NotEmpty
    private Integer colorCode;

    @Column(name="degree",nullable = true)
    @NotEmpty
    private float degree;

    @Column(name="is_sale",nullable = false)
    @NotEmpty
    private Integer isSale;

    @Column(name="moisture")
    @NotEmpty
    private float moisture;

    @Column(name="basecurve")
    @NotEmpty
    private float basecurve;

    @Column(name="recommend")
    @NotEmpty
    private Integer recommend;


}