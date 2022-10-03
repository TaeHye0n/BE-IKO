package com.iko.iko.controller.product.dto.request;

import com.iko.iko.domain.entity.Product;
import com.iko.iko.domain.entity.ProductDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.iko.iko.common.util.EntityListUtil.convertStringListToString;


public class ProductRequest {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class ProductSaveRequest {
        private String productName;
        private Integer price;
        private Integer discount;
        private Float diameter;
        private String manufacturer;
        private String series;
        private List<String> feature;
        private Integer recommend;
        private Integer exposure;
        private List<ProductOptionSaveRequest> productOptionSaveRequestList;

        @Getter
        @Setter
        @Builder
        @AllArgsConstructor
        public static class ProductOptionSaveRequest {
            private Float graphicDiameter;
            private Float basecurve;
            private String color;
            private String colorCode;
            private String material;
            private Integer detailsPrice;
            private Integer moisture;
            private Integer productDetailsStock;
            private Integer isSale;
            private Integer detailsExposure;
            private Integer period;
            private List<Float> degree;
            private List<String> imageUrl;
            private List<String> explanationImageUrl;

            @Builder
            public ProductDetails toEntity() {
                return ProductDetails.builder()
                        .graphicDiameter(graphicDiameter)
                        .basecurve(basecurve)
                        .color(color)
                        .colorCode(colorCode)
                        .material(material)
                        .detailsPrice(detailsPrice)
                        .moisture(moisture)
                        .productDetailsStock(productDetailsStock)
                        .isSale(isSale)
                        .detailsExposure(detailsExposure)
                        .period(period)
                        .build();
            }
        }

        @Builder
        public Product toEntity() {
            return Product.builder()
                    .name(productName)
                    .price(price)
                    .discount(discount)
                    .diameter(diameter)
                    .manufacturer(manufacturer)
                    .series(series)
                    .feature(convertStringListToString(feature))
                    .recommend(recommend)
                    .exposure(exposure)
                    .build();
        }
    }
}
