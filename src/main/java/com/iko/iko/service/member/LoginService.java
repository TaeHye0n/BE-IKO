package com.iko.iko.service.member;


import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.controller.member.dto.request.MemberSignInRequestDto;
import com.iko.iko.controller.member.dto.response.TokenResponseDto;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponseDto login(MemberSignInRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.USER_NOT_FOUND));
        validateMatchedPassword(requestDto.getPassword(), member.getPassword());

        //TODO : Access Token 과 Refresh Token 을 생성합니다.
        String accessToken = jwtTokenProvider.createAccessToken(member.getUsername(), member.getRole().name());
        String refreshToken = jwtTokenProvider.createRefreshToken(member.getUsername());
        //TODO : Refresh Token 을 DB에 저장합니다.
        member.updateRefreshToken(refreshToken);
        memberRepository.save(member);

        // 밀리세컨드를 나타내기 위한 format
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
//        Date date = jwtTokenProvider.getExpiredDate(accessToken);
//        Date dateTwo = jwtTokenProvider.getExpiredDate(refreshToken);


        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpiredDate(jwtTokenProvider.getACCESS_TOKEN_VALID_TIME())
                .refreshTokenExpiredDate(jwtTokenProvider.getREFRESH_TOKEN_VALID_TIME())
                .build();
    }

    private void validateMatchedPassword(String validPassword, String memberPassword) {
        if(!passwordEncoder.matches(validPassword,memberPassword)){
            throw new BaseException(ErrorCode.WRONG_PASSWORD);
        }
    }
}

