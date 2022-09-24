package com.iko.iko.service.member;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.controller.member.dto.request.UpdateInfoRequestDto;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.security.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateInfoService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long updateInfo(UpdateInfoRequestDto requestDto){
        Member member = validateLoginStatus();
        validateMatchedPassword(requestDto.getPassword(), member.getPassword());
        return memberRepository.updateInfo(requestDto);
    }

    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }

    private void validateMatchedPassword(String validPassword, String memberPassword) {
        if(!passwordEncoder.matches(validPassword,memberPassword)){
            throw new BaseException(ErrorCode.WRONG_PASSWORD);
        }
    }

}
