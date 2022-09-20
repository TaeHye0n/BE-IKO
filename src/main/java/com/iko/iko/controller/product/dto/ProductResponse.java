package com.iko.iko.controller.product.dto;

import com.iko.iko.domain.entity.Product;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.iko.iko.controller.product.dto.ProductResponseMapper.INSTANCE;
public class ProductResponse {

    @Getter
    @Builder
    @AllArgsConstructor
    public static  class ProductMainResponse{
        private  Integer productId;
        private  String name;
        private String imageUrl;
        private  String manufacturer;
        private String series;
        private  String feature;
        private Integer discount;
        private Integer price;
        private float graphicDiameter;
    }


}
