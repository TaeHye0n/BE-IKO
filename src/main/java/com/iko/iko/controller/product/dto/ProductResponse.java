package com.iko.iko.controller.product.dto;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import lombok.*;

import java.util.List;
public class ProductResponse {

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

    public class ProductMainResponse {
    }

        /*
        private ProductResponse product;
        private List<imageResponse.ImageUrlByImageId> imageUrl;

        private List<ProductDetailsResponse.colorCodeMainProduct> colorCode;

        public ProductMainResponse(Product product, List<imageResponse.ImageUrlByImageId> imageUrl,
                                   List<ProductDetailsResponse.colorCodeMainProduct> colorCode){
            this.product =INSTANCE.ofProduct(product);
            this.imageUrl=imageUrl;
            this.colorCode=colorCode;
        }*/



}