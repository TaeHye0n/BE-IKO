package com.iko.iko.controller.ProductDetails.dto;

import com.iko.iko.controller.image.dto.ImageResponse;
import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.domain.entity.*;
import lombok.*;

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
        private Float diameter;
        private String colorCode;
        private Integer price;
        private Integer discount;
        private String imageUrl;
        private Integer duration;

    }


    @Getter
    @AllArgsConstructor
    @Builder
    @Setter
    public static class ProductMainByOption{
        private Integer productId;
        private String series;
        private Integer price;
        private Integer discount;
        private Float graphicDiameter;
        private String colorCode;
        private String imageUrl;

    }
    @Getter
    @AllArgsConstructor
    @Builder
    @Setter
    public static class ProductMainByOptionResponse{
        private Integer productId;
        private String series;
        private Integer price;
        private Integer discount;
        private Float graphicDiameter;
        private String colorCode;
        private List<String> imageUrl;

    }
}
