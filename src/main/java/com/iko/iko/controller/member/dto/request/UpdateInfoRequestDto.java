package com.iko.iko.controller.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateInfoRequestDto {

    private String address;

    private Integer memberId;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    private String phone;

    private Integer postCode;

    @NotNull(message = "이름 읽는 법을 입력해주세요.")
    private String readname;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

    private String birthday;
}
