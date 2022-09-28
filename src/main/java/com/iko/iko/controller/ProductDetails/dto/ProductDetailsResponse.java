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
    public static  class MainProduct{
        private Integer productId;
        private String series;
        private Float graphicDiameter;
        private Integer price;
        private Integer discount;
        private String colorCode;
        private String imageUrl;
    }
    @Setter
    @Getter
    @AllArgsConstructor
    @Builder
    public static  class MainProductForResponse{
        private Integer productId;
        private String series;
        private List<Float> graphicDiameter;
        private Integer price;
        private Integer discount;
        private List<String> colorCode;
        private List<String> imageUrl;
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
        private List<String> colorCode;
        private List<String> imageUrl;

    }
}
