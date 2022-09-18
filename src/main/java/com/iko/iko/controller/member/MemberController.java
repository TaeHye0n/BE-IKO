package com.iko.iko.controller.member;

import com.iko.iko.controller.member.dto.request.MemberSignInRequestDto;
import com.iko.iko.controller.member.dto.request.MemberSignUpRequestDto;
import com.iko.iko.controller.member.dto.response.TokenResponseDto;
import com.iko.iko.service.member.facade.MemberFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberController {

    private final MemberFacade memberFacade;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public Integer signUp(@Valid @RequestBody MemberSignUpRequestDto requestDto) {
        return memberFacade.signUp(requestDto);
    }

    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody @Valid MemberSignInRequestDto requestDto) {
        return memberFacade.login(requestDto);
    }


}
