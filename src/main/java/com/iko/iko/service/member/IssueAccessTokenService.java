package com.iko.iko.service.member;


import com.iko.iko.controller.member.dto.response.TokenResponseDto;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Service
@Slf4j
public class IssueAccessTokenService {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponseDto issueAccessToken(HttpServletRequest request) {
        //TODO : 만료된 accessToken 과 refreshToken 을 가져옴
        String accessToken = jwtTokenProvider.resolveAccessToken(request);
        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);

        //TODO : accessToken 이 만료되었으면
        if(jwtTokenProvider.validateAccessToken(accessToken)) {
            log.info("access 토큰 만료됨");
            //TODO : 만약 refreshToken 이 유효하다면
            if(jwtTokenProvider.validateRefreshToken(refreshToken)) {
                log.info("refresh Token 은 유효합니다.");

                //TODO : DB에 저장해두었던 refreshToken 을 불러오고 새로운 Access Token 을 생성하기 위함
                Member member = memberRepository.findByEmail(jwtTokenProvider.getUserEmail(refreshToken))
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

                //TODO : 만약 DB refreshToken 와 요청한 refreshToken 가 같다면
                if(refreshToken.equals(member.getRefreshToken())) {
                    //TODO : 새로운 accessToken 생성
                    accessToken = jwtTokenProvider.createAccessToken(member.getEmail(), member.getRole().name());
                }
                else {
                    log.info("토큰이 변조되었습니다.");
                }
            }
            else {
                log.info("Refresh Token 이 유효하지 않습니다.");
            }
        }
        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenValidTime(18000L)
                .refreshTokenValidTime(604800L)
                .build();
    }
}
