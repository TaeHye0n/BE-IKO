package com.iko.iko.controller.ProductDetails.dto;
import lombok.*;

import java.util.List;

public class ProductDetailsRequest {

    @Setter
    @Getter
    @AllArgsConstructor
    @Builder
    public static class ProductOptionForRequest{

        private List<Integer> period;

        private List<Float> graphicDiameter;

        private List<String> colorCode;

        private List<String> series;

        private List<String> feature;


    }
    @Setter
    @Getter
    @AllArgsConstructor
    @Builder
    public static class ProductDetailsForRequest{
        private Integer productId;
        private Integer period;
        private String colorCode;
        private Float graphicDiameter;
        private Float degree;
    }



}
