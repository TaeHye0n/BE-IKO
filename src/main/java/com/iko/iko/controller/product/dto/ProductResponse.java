package com.iko.iko.controller.product.dto;

import lombok.*;

import java.util.Date;
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

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProductInfoResponse{
        private Integer productId;
        private String productName;
        private Integer price;
        private String series;
        private Integer discount;
        private String manufacturer;
        private Float diameter;
        private Integer recommend;
        private Integer exposure;
        private String[] feature;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProductInfo{
        private Integer productId;
        private String productName;
        private Integer price;
        private String series;
        private Integer discount;
        private String manufacturer;
        private Float diameter;
        private Integer recommend;
        private Integer exposure;
    }
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProductIdAndCreatedAt{
        private Integer productId;
        private Date createdAt;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProductDetailsInfoResponse{
        private ProductInfoResponse productInfo;
        private List<ProductDetailsInfoWithImage> detailsInfo;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProductDetailsInfoWithImage {
        private Float graphicDiameter;
        private Float basecurve;
        private String color;
        private String colorCode;
        private String material;
        private Integer detailsPrice;
        private Integer moisture;
        private Integer isSale;
        private Integer detailsExposure;
        private Integer period;
        private Integer productDetailsStock;
        private List<Float> degree;
        private List<String> imageUrl;
        private List<String> explanationImageUrl;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ProductDetailsInfo{
        private Float graphicDiameter;
        private Float basecurve;
        private String color;
        private String colorCode;
        private String material;
        private Integer detailsPrice;
        private Integer moisture;
        private Integer isSale;
        private Integer detailsExposure;
        private Integer period;
        private Integer productDetailsStock;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class stockListResponse{
        private Float degree;
        private Integer stock;
        private Integer productDetailsId;
    }


}