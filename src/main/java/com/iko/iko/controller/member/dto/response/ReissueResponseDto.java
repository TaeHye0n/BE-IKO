package com.iko.iko.controller.member.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReissueResponseDto {

    private String accessToken;

    private String refreshToken;

    private long accessTokenExpiredDate;

    private long refreshTokenExpiredDate;


}
