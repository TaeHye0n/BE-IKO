package com.iko.iko.controller.member.dto.response;

import com.iko.iko.security.jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenResponseDto {

    private String accessToken;

    private String refreshToken;

    private Long accessTokenValidTime;

    private Long refreshTokenValidTime;


}
