package com.iko.iko.service.member;


import com.iko.iko.controller.member.dto.request.MemberSignInRequestDto;
import com.iko.iko.controller.member.dto.response.TokenResponseDto;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    @Transactional
    public TokenResponseDto login(MemberSignInRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입된 이메일이 아닙니다."));
        validateMatchedPassword(requestDto.getPassword(), member.getPassword());

        //TODO : Access Token 과 Refresh Token 을 생성합니다.
        String accessToken = jwtTokenProvider.createAccessToken(member.getUsername(), member.getRole().name());
        String refreshToken = jwtTokenProvider.createRefreshToken();

        //TODO : Refresh Token 을 DB에 저장합니다.
        member.updateRefreshToken(refreshToken);
        memberRepository.save(member);

        return TokenResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void validateMatchedPassword(String validPassword, String memberPassword) {
        if(!passwordEncoder.matches(validPassword,memberPassword)){
            throw new IllegalArgumentException("잘못된 비밀번호입니다,");
        }
    }
}

