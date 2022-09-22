package com.iko.iko.controller.image.dto;


import com.iko.iko.controller.product.dto.ProductResponse;
import com.iko.iko.domain.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class imageResponse {

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor

    public static class ImageUrl {
        private String ImageUrl;

        public static class ImageUrlFromProductId {
            private String imageUrl;

        }
    }
}
