package com.iko.iko.service.member.facade;

import com.iko.iko.controller.member.dto.request.MemberSignInRequestDto;
import com.iko.iko.controller.member.dto.request.MemberSignUpRequestDto;
import com.iko.iko.controller.member.dto.response.TokenResponseDto;
import com.iko.iko.service.member.LoginService;
import com.iko.iko.service.member.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberFacade {

    private final SignupService signupService;
    private final LoginService loginService;

    @Transactional
    public Integer signUp(MemberSignUpRequestDto requestDto){
         return signupService.signUp(requestDto);
    }

    @Transactional
    public TokenResponseDto login(MemberSignInRequestDto requestDto){
        return loginService.login(requestDto);
    }

}
