package com.iko.iko.controller.ProductDetails.dto;

import lombok.*;

import java.util.List;
public class ProductDetailsResponse {

    @Setter
    @Getter
    @AllArgsConstructor
    @Builder
    public static class GetGraphicDiameter{
        private Float graphicDiameter;
    }
    @Setter
    @Getter
    @AllArgsConstructor
    @Builder
    public static class GetColorCodeAndImageUrl{
        private String colorCode;
        private String imageUrl;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @Builder
    public static  class MainProduct{
        private Integer productDetailsId;
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
        private List<GetColorCodeAndImageUrl> colorAndImage;
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
    @Setter
    @Getter
    @AllArgsConstructor
    @Builder
    public static class ProductDetails {
        private Integer productId;
        private Integer productDetailsId;
        private String name;
        private String series;
        private Float diameter;
        private String colorCode;
        private Integer price;
        private Integer discount;
        private String imageUrl;
        private Integer imageType;
        private Float degree;
        private Float graphicDiameter;
    }

    @Setter
    @Getter
    @AllArgsConstructor
    @Builder
    public static class ProductDetailsForResponse {
        private Integer productId;
        private Integer productDetailsId;
        private String name;
        private String series;
        private Float diameter;
        private List<String> colorCode;
        private Integer price;
        private Integer discount;
        private List<String> imageUrlOne;
        private List<String> imageUrlTwo;
        private List<String> degree;
        private List<String> graphicDiameter;

    }

}
