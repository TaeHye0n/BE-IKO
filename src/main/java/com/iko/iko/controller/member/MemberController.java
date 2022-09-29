package com.iko.iko.controller.member;

import com.iko.iko.controller.member.dto.request.MemberSignInRequestDto;
import com.iko.iko.controller.member.dto.request.MemberSignUpRequestDto;
import com.iko.iko.controller.member.dto.request.UpdateInfoRequestDto;
import com.iko.iko.controller.member.dto.request.UpdatePasswordRequestDto;
import com.iko.iko.controller.member.dto.response.MemberResponseDto;
import com.iko.iko.controller.member.dto.response.MyOrderListResponseDto;
import com.iko.iko.controller.member.dto.response.ReissueResponseDto;
import com.iko.iko.controller.member.dto.response.TokenResponseDto;
import com.iko.iko.service.member.facade.MemberFacade;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@RequestMapping("/member")
@RestController
public class MemberController {

    private final MemberFacade memberFacade;

    public MemberController(MemberFacade memberFacade) {
        this.memberFacade = memberFacade;
    }

    // 회원가입
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.OK)
    public Integer signUp(@Valid @RequestBody MemberSignUpRequestDto requestDto) {
        return memberFacade.signUp(requestDto);
    }

    // 로그인
    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody @Valid MemberSignInRequestDto requestDto) {
        return memberFacade.login(requestDto);
    }


    // 토큰 재발급
    @ApiOperation(value = "토큰 재발급", notes = "토큰을 재발급한다")
    @PutMapping("/newAccess")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-ACCESS-TOKEN", value = "access-token", required = true, dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "X-REFRESH-TOKEN", value = "refresh-token", required = true, dataType = "String", paramType = "header")
    })
    public ReissueResponseDto issueAccessToken(
            @RequestHeader(value="X-ACCESS-TOKEN") String token,
            @RequestHeader(value="X-REFRESH-TOKEN") String refreshToken ) {
        return memberFacade.issueAccessToken(token, refreshToken);
    }

    // 유저정보 제공
    @GetMapping("/info")
    public MemberResponseDto findMyInfo(){
        return memberFacade.findMyInfo();
    }

    // 유저정보 수정
    @PutMapping("/updateInfo")
    public ResponseEntity updateInfo(@RequestBody @Valid UpdateInfoRequestDto requestDto){
         memberFacade.updateInfo(requestDto);
        return new ResponseEntity<>("회원정보 변경 완료", HttpStatus.OK);
    }

    // 비밃번호 수정
    @PutMapping("/updatePassword")
    public ResponseEntity updatePassword(@RequestBody @Valid UpdatePasswordRequestDto requestDto){
        memberFacade.updatePassword(requestDto);
        return new ResponseEntity<>("비밀번호 변경 완료", HttpStatus.OK);
    }

    // 마이페이지 주문 내역
    @ApiOperation(value = "유저 주문내역", notes = "현재 더미 데이터상 member_id_pk 23번만 사용가능")
    @GetMapping("/myOrderList")
    public List<MyOrderListResponseDto> MyOrderList(){
        return memberFacade.MyOrderList();
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity logout (){
        memberFacade.logout();
        return new ResponseEntity<>("로그아웃 완료", HttpStatus.OK);
    }

    @PostMapping("/myOrderCancel")
    public ResponseEntity orderCancel (@RequestBody @Valid Integer orderId){
        memberFacade.orderCancel(orderId);
        return new ResponseEntity<>("취소 완료", HttpStatus.OK);
    }

}
