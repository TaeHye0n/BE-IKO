package com.iko.iko.service.member;

import com.iko.iko.controller.member.dto.request.MemberSignUpRequestDto;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Integer signUp(MemberSignUpRequestDto requestDto)  {

        if (memberRepository.findByEmail(requestDto.getEmail()).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        if (!requestDto.getPassword().equals(requestDto.getPasswordConfirm())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        Member member = memberRepository.save(requestDto.toEntity());
        member.encodePassword(passwordEncoder);
        member.addUserAuthority();
        return member.getMemberId();
    }
}
