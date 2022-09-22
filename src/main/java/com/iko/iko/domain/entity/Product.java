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
    @NotEmpty
    private String manufacturer;

    @Column(name="series", nullable = false)
    @NotEmpty
    private String series;

    @Column(name="feature", nullable = false)
    @NotEmpty
    private String feature;

    @Column(name="discount", nullable = false)
    @NotEmpty
    private Integer discount;

    @Column(name="product_stock",nullable = false)
    @NotEmpty
    private Integer stock;

    @Column(name ="product_name", nullable = false)
    @NotEmpty
    private String name;

    @Column(name="price", nullable = false)
    @NotEmpty
    private Integer price;

    @Column(name ="recommend",nullable = false)
    @NotEmpty
    private Integer recommend;

    @Column(name ="exposure" , nullable = false)
    @NotEmpty
    private Integer exposure;

    @Column(name="diameter" , nullable = false)
    @NotEmpty
    private Float diameter;

}
