package com.iko.iko.controller.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePasswordRequestDto {

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String newPassword;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String newPasswordConfirm;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

}
