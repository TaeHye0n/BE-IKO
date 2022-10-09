package com.iko.iko.controller.product.dto;

import lombok.*;

import java.util.List;
public class ProductResponse {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class GetAllProductDistinct{
        private Integer productId;
        private String series;
        private Integer price;
        private Integer discount;
        private String name;
    }
    @Getter
    @Builder
    @AllArgsConstructor
    public static class ProductForResponse{
        private Integer productId;
        private Integer imageId;
        private String name;
        private String manufacturer;
        private Integer recommend;
        private String series;
        private String feature;
        private Integer stock;
        private Integer price;
        private float graphicDiameter;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class AllProduct{
        private String name;
        private String series;
        private Integer price;
        private Integer discount;
        private float diameter;
        private String feature;
        //private List<ProductDetailsResponse.ProductDetailsForResponse> productDetailsId;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @Builder
    public static class productDetailsIdFromProductId{
        private Integer productId;

    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class productInfoForProductDetailsMain {
        private String name;
        private String series;
        private Integer price;
        private Integer discount;
        private String mainImageUrl;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public  static class typeAndImage{
        private Integer imageType;
        private String imageUrl;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class productFilterList{
        private String series;
        private String feature;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class productFilter{
        private List<String> series;
        private List<String> feature;
        private List<Integer> period;
        private List<Float> graphicDiameter;
        private List<String> colorCode;

    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class recommendedProduct{
        private String name;
        private Integer price;
        private Integer discount;
        private String imageUrl;
    }

}