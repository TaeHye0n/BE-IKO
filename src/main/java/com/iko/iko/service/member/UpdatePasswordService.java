package com.iko.iko.service.member;


import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.controller.member.dto.request.UpdatePasswordRequestDto;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.security.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdatePasswordService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Integer updatePassword(UpdatePasswordRequestDto requestDto){
        if (!requestDto.getNewPassword().equals(requestDto.getNewPasswordConfirm())){
            throw new BaseException(ErrorCode.PASSWORD_NOT_MATCHED);
        }
        Member member = validateLoginStatus();
        validateMatchedPassword(requestDto.getPassword(), member.getPassword());
        member.updatePassword(passwordEncoder, requestDto.getNewPassword());
        return member.getMemberId();
    }

    private void validateMatchedPassword(String validPassword, String memberPassword) {
        if(!passwordEncoder.matches(validPassword,memberPassword)){
            throw new BaseException(ErrorCode.WRONG_PASSWORD);
        }
    }

    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }
}
