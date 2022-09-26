package com.iko.iko.controller.ProductDetails.dto;

import com.iko.iko.controller.image.dto.ImageResponse;
import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.domain.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
public class ProductDetailsResponse {

    @Setter
    @Getter
    @AllArgsConstructor
    @Builder
    public static class ProductDetailsForResponse {
        //private Product product;
        private Integer productId;
        private String series;
        private String feature;
        private float diameter;
        private String colorCode;
        private Integer price;
        private Integer discount;
        private String imageUrl;
        private Integer duration;
    }
}
