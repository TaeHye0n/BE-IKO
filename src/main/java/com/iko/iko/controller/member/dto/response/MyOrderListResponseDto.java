package com.iko.iko.controller.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyOrderListResponseDto {

    private Integer OrderId;
    private Integer status;
    private String name;
    private String color;
    private String colorCode;
    private Float graphicDiameter;
    private Float degree;
    private Integer price;
    private Integer period;
    private String imageUrl;


}
