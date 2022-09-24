package com.iko.iko.service.member.facade;

import com.iko.iko.controller.member.dto.request.MemberSignInRequestDto;
import com.iko.iko.controller.member.dto.request.MemberSignUpRequestDto;

import com.iko.iko.controller.member.dto.request.UpdateInfoRequestDto;
import com.iko.iko.controller.member.dto.request.UpdatePasswordRequestDto;
import com.iko.iko.controller.member.dto.response.MemberResponseDto;
import com.iko.iko.controller.member.dto.response.ReissueResponseDto;
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
    private final UpdateInfoService updateInfoService;
    private final UpdatePasswordService updatePasswordService;

    @Transactional
    public Integer signUp(MemberSignUpRequestDto requestDto){
         return signupService.signUp(requestDto);
    }

    @Transactional
    public TokenResponseDto login(MemberSignInRequestDto requestDto){
        return loginService.login(requestDto);
    }

    @Transactional
    public ReissueResponseDto issueAccessToken(String token, String refreshToken){
        return issueAccessTokenService.issueAccessToken(token, refreshToken);
    }

    @Transactional
    public MemberResponseDto findMyInfo(){
        return infoService.findMyInfo();
    }

    @Transactional
    public Long updateInfo(UpdateInfoRequestDto requestDto){
        return updateInfoService.updateInfo(requestDto);
    }

    @Transactional
    public Integer updatePassword(UpdatePasswordRequestDto requestDto){
        return updatePasswordService.updatePassword(requestDto);
    }

}
