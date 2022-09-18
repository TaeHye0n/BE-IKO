package com.iko.iko.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_product")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="product_id_pk",unique = true,nullable = false)
    private Integer product_id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "image_id_fk")
    })
    private Image image_id;

    @Column(name ="name", nullable = false)
    @NotEmpty
    private String name;


    @Column(name ="manufacturer", nullable = false)
    @NotEmpty
    private String manufacturer;

    @Column(name ="recommend", nullable = true)
    @NotEmpty
    private boolean recommend;

    @Column(name="series", nullable = false)
    @NotEmpty
    private String series;

    @Column(name="feature", nullable = false)
    @NotEmpty
    private String feature;

    @Column(name="discount",nullable = true)
    @NotEmpty
    private Integer discount;


}
