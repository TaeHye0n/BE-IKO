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
    private Integer productDetailsId;

    @Column(name="product_id_fk")
    private Integer productIdFk;

    @Column(name="product_details_stock")
    private Integer productDetailsStock;

    @Column(name ="color", nullable = false)
    @NotEmpty
    private String color;

    @Column(name ="period", nullable = false)
    @NotEmpty
    private Integer period;

    @Column(name ="details_price", nullable = false)
    @NotEmpty
    private Integer detailsPrice;

    @Column(name="material", nullable = false)
    @NotEmpty
    private String material;

    @Column(name="color_code", nullable = false)
    @NotEmpty
    private String colorCode;

    @Column(name="degree", nullable = false)
    @NotEmpty
    private float degree;

    @Column(name="graphic_diameter" , nullable = false)
    @NotEmpty
    private float graphicDiameter;

    @Column(name="is_sale",nullable = false)
    @NotEmpty
    private Integer isSale;

    @Column(name="moisture" , nullable = false)
    @NotEmpty
    private Integer moisture;

    @Column(name="basecurve" , nullable = false)
    @NotEmpty
    private float basecurve;

    @Column(name="sold_out" , nullable = false)
    @NotEmpty
    private Integer soldOut;

    @Column(name="details_exposure" , nullable = false)
    @NotEmpty
    private Integer detailsExposure;

}