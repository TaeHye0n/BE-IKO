package com.iko.iko.controller.ProductDetails.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class ProductDetailsResponse {

    @Setter
    @Getter
    @AllArgsConstructor
    @Builder
    public static class colorCodeMainProduct{
        private Integer colorCode;
    }
}
