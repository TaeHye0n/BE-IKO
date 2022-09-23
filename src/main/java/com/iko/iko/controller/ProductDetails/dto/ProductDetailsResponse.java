package com.iko.iko.controller.ProductDetails.dto;

import com.iko.iko.controller.image.dto.ImageResponse;
import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.domain.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
public class ProductDetailsResponse {

    @Setter
    @Getter
    @AllArgsConstructor
    @Builder
    public static class ProductDetailsForResponse {

        private Integer productDetailsId;

        private ProductResponse.ProductForResponse product;
        private ImageResponse.ImageForResponse image;
    }
}
