package com.iko.iko.controller.cart.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartListResponseDto {

    private String name;
    private String color;
    private String colorCode;
    private Float graphicDiameter;
    private Float degree;
    private Integer price;
    private Integer discount;
    private Integer period;
    private String imageUrl;

}
