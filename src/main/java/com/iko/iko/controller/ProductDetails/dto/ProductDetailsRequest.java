package com.iko.iko.controller.ProductDetails.dto;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductDetailsRequest {

    @Setter
    @Getter
    @AllArgsConstructor
    @Builder
    public static class ProductOptionForRequest{
        private Integer period;
        private float graphicDiameter;
        private String colorCode;
        private String series;
        private String feature;

        public List<List<String>> ListDtoToListEntity(List<String> period){
            return Stream
                    .of(period)
                    .collect(Collectors.toList());
        }
    }
}
