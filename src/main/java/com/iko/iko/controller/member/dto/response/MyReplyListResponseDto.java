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
public class MyReplyListResponseDto {

    private Date replyCreatedAt;
    private Integer orderId;
    private Integer replyId;
    private String name;
    private String color;
    private String colorCode;
    private Float graphicDiameter;
    private Float degree;
    private Integer price;
    private Integer period;
    private String replyContent;
    private Float replyRating;
    private String orderImageUrl;
    private String replyImageUrl;
}
