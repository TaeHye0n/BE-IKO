package com.iko.iko.domain.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@DynamicUpdate
@DynamicInsert
@ToString
@Table(name = "tb_product")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="product_id_pk",unique = true,nullable = false)
    private Integer productId;

    @Column(name ="manufacturer", nullable = false)
    private String manufacturer;

    @Column(name="series", nullable = false)
    private String series;

    @Column(name="feature", nullable = false)
    private String feature;

    @Column(name="discount", nullable = false)
    private Integer discount;

    @Column(name="product_stock",nullable = false)
    private Integer stock;

    @Column(name ="product_name", nullable = false)
    private String name;

    @Column(name="price", nullable = false)
    private Integer price;

    @Column(name ="recommend",nullable = false)
    private Integer recommend;

    @Column(name ="exposure" , nullable = false)
    private Integer exposure;

    @Column(name="diameter" , nullable = false)
    private Float diameter;

}
