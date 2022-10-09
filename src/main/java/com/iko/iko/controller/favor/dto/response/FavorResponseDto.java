package com.iko.iko.controller.favor.dto.response;

import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class FavorResponseDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class GetFavorResponse {
        private GetProductInfoForFavorResponse productInfo;
        private List<Float> graphicDiameter;
        private List<GetColorAndImageUrlForFavorResponse> colorAndImageInfo;
        private List<Integer> period;
        private Timestamp favorCreatedAt;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class GetProductInfoForFavorResponse {
        private Integer productId;
        private String productName;
        private Integer price;
        private Integer discount;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class GetGraphicDiameterForFavorResponse {
        private Float graphicDiameter;
    }


    @Setter
    @Getter
    @AllArgsConstructor
    @Builder
    public static class GetColorAndImageUrlForFavorResponse {
        private String color;
        private String colorCode;
        private String imageUrl;
    }

}
