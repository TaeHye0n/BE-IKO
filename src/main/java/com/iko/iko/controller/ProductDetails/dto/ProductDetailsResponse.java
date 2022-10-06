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
        private Integer totalCount;
        private Integer isFavorite;
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
        private String colorCode;
        private Float degree;
        private Float graphicDiameter;
        private Integer period;
    }


    @Getter
    @AllArgsConstructor
    @Builder
    public static class typeAndImage{
        private Integer imageType;
        private String imageUrl;
    }
    @Getter
    @AllArgsConstructor
    @Builder
    public static class typeAndImageList{
        private Integer imageType;
        private List<String> imageUrl;
    }
    @Setter
    @Getter
    @AllArgsConstructor
    @Builder
    public static class ProductDetailsForImageList{
        private typeAndImage typeOneImage;
        private typeAndImageList typeTwoImage;
        private typeAndImage typeThreeImage;

        public ProductDetailsForImageList() {
            this.typeOneImage=getTypeOneImage();
            this.typeTwoImage=getTypeTwoImage();
            this.typeThreeImage=getTypeThreeImage();
        }
    }
    @Setter
    @Getter
    @AllArgsConstructor
    @Builder
    public static class ProductDetailsForResponse {
        private Integer isFavorite;
        private Integer productId;
        private String name;
        private String series;
        private Integer Price;
        private Integer discount;
        private String mainImageUrl;
        private List<String> subMainImageUrlList;
        private List<Integer> periodList;
        private List<String> colorCodeList;
        private List<Float> graphicDiameterList;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class ListInfoForProductDetails{
        private Integer period;
        private String colorCode;
        private Float graphicDiameter;
        private Float degree;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class ProductDetailsFilterList{
        private Integer period;
        private Float graphicDiameter;
        private String colorCode;

    }

}
