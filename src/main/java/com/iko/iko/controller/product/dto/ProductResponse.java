package com.iko.iko.controller.product.dto;

import com.iko.iko.controller.ProductDetails.dto.ProductDetailsResponse;
import com.iko.iko.domain.entity.Product;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.iko.iko.controller.image.dto.imageResponse;

import java.util.List;

import static com.iko.iko.controller.product.dto.ProductResponseMapper.INSTANCE;
public class ProductResponse {

    @Getter
    @Builder
    @AllArgsConstructor

    public static  class ProductMainResponse{

        private  String productName;
        private List<imageResponse.ImageUrl> imageUrl;

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
        private List<ProductDetailsResponse.colorCodeMainProduct> colorCode;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class AllProduct{
        private String name;
        private Integer price;
        private Integer discount;
        private String image;
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
