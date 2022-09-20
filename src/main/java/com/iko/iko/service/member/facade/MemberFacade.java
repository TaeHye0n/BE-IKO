package com.iko.iko.service.member.facade;

import com.iko.iko.controller.member.dto.request.MemberSignInRequestDto;
import com.iko.iko.controller.member.dto.request.MemberSignUpRequestDto;
import com.iko.iko.controller.member.dto.response.MemberResponseDto;
import com.iko.iko.controller.member.dto.response.TokenResponseDto;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.service.member.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class MemberFacade {

    private final SignupService signupService;
    private final LoginService loginService;
    private final IssueAccessTokenService issueAccessTokenService;
    private final InfoService infoService;

    @Transactional
    public Integer signUp(MemberSignUpRequestDto requestDto){
         return signupService.signUp(requestDto);
    }

    @Transactional
    public TokenResponseDto login(MemberSignInRequestDto requestDto){
        return loginService.login(requestDto);
    }

    @Transactional
    public TokenResponseDto issueAccessToken(HttpServletRequest request){
        return issueAccessTokenService.issueAccessToken(request);
    }

    @Transactional
    public MemberResponseDto findMyInfo(){
        return infoService.findMyInfo();
    }

}
