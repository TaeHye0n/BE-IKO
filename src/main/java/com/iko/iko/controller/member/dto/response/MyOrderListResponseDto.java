package com.iko.iko.controller.member.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyOrderListResponseDto {

    private Date orderCreatedAt;
    private Integer OrderId;
    private Integer status;
    private String name;
    private String color;
    private String colorCode;
    private String graphicDiameter;
    private Float degree;
    private Integer totalPrice;
    private Integer period;


}
