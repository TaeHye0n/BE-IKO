package com.iko.iko.service.member;


import com.iko.iko.controller.member.dto.response.ReissueResponseDto;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;

@RequiredArgsConstructor
@Service
@Slf4j
public class IssueAccessTokenService {
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

//    @Transactional
//    public ReissueResponseDto issueAccessToken(String token, String refreshToken) {
//        if (jwtTokenProvider.validateTokenExceptExpiration(token))
//            throw new IllegalArgumentException("엑세스 토큰이 만료되지 않았습니다.");
//
//        Member member = memberRepository.findByEmail(String.valueOf(jwtTokenProvider.getUserEmail(refreshToken)))
//                .orElseThrow(() -> new IllegalArgumentException("유저가 없습니다."));
//
//        if (!jwtTokenProvider.validateToken(member.getRefreshToken()) || !refreshToken.equals(member.getRefreshToken()))
//            throw new IllegalArgumentException("리프레시 토큰이 유효하지 않습니다.");
//
//        token = jwtTokenProvider.createAccessToken(member.getEmail(), member.getRole().name());
//
//        return ReissueResponseDto.builder()
//                .accessToken(token)
//                .accessTokenValidTime(1800000L)
//                .build();
//    }

    @Transactional
    public ReissueResponseDto issueAccessToken(String token, String refreshToken){
        if(jwtTokenProvider.validateTokenExceptExpiration(token)){
            Member member = memberRepository.findByEmail(String.valueOf(jwtTokenProvider.getUserEmail(refreshToken)))
                    .orElseThrow(() -> new IllegalArgumentException("해당 유저가 없습니다."));

            if(jwtTokenProvider.validateToken(member.getRefreshToken()) && refreshToken.equals(member.getRefreshToken())){
                token = jwtTokenProvider.createAccessToken(member.getEmail(), member.getRole().name());
            }
            else throw new IllegalArgumentException("리프레시 토큰이 유효하지 않습니다.");
        }

        return ReissueResponseDto.builder()
                .accessToken(token)
                .accessTokenValidTime(1800000L)
                .build();
    }
}



//    @Transactional
//    public ReissueResponseDto issueAccessToken(String token, String refreshToken){
//        if(!jwtTokenProvider.validateToken(refreshToken)) throw new IllegalArgumentException("리프레시 토큰이 만료 되었습니다.");
//        Authentication authentication = jwtTokenProvider.getAuthentication(refreshToken);
//        Member member = memberRepository.findByEmail(String.valueOf(authentication.getName()))
//                .orElseThrow(() -> new IllegalArgumentException("유저가 없습니다."));
//        if(!jwtTokenProvider.validateToken(member.getRefreshToken()) || !refreshToken.equals(member.getRefreshToken()))
//            throw new IllegalArgumentException("리프레시 토큰이 유효하지 않습니다.");
//        token = jwtTokenProvider.createAccessToken(member.getEmail(), member.getRole().name());
//
//        return ReissueResponseDto.builder()
//                .accessToken(token)
//                .accessTokenValidTime(1800L)
//                .build();
//    }
//}

//    public ReissueResponseDto issueAccessToken(String token, String refreshToken) {
//        //TODO : 만료된 accessToken 과 refreshToken 을 가져옴
//
//        //TODO : accessToken 이 만료되었으면
//        if (jwtTokenProvider.validateTokenExceptExpiration(token)) {
//            log.info("access 토큰 만료됨");
//            //TODO : 만약 refreshToken 이 유효하다면
//            if (jwtTokenProvider.validateToken(refreshToken)) {
//                log.info("refresh Token 은 유효합니다.");
//
//                //TODO : DB에 저장해두었던 refreshToken 을 불러오고 새로운 Access Token 을 생성하기 위함
//                Member member = memberRepository.findByEmail(jwtTokenProvider.getUserEmail(refreshToken))
//                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));
//
//                //TODO : 만약 DB refreshToken 와 요청한 refreshToken 가 같다면
//                if (refreshToken.equals(member.getRefreshToken())) {
//                    //TODO : 새로운 accessToken 생성
//                    token = jwtTokenProvider.createAccessToken(member.getEmail(), member.getRole().name());
//                } else {
//                    log.info("토큰이 변조되었습니다.");
//                }
//            } else {
//                log.info("Refresh Token 이 유효하지 않습니다.");
//            }
//        }
//
//        return ReissueResponseDto.builder()
//                .accessToken(token)
//                .accessTokenValidTime(1800000L)
//                .build();
//    }
//}

