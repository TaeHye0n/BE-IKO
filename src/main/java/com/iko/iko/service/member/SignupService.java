package com.iko.iko.service.member;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.controller.member.dto.request.MemberSignUpRequestDto;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.repository.member.MemberRepository;
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
            throw new BaseException(ErrorCode.EMAIL_DUPLICATED_ERROR);
        }

        if (!requestDto.getPassword().equals(requestDto.getPasswordConfirm())){
            throw new BaseException(ErrorCode.PASSWORD_NOT_MATCHED);
        }

        Member member = memberRepository.save(requestDto.toEntity());
        member.encodePassword(passwordEncoder);
        member.addUserAuthority();
        return member.getMemberId();
    }
}
