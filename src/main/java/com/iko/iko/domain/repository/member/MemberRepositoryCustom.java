package com.iko.iko.domain.repository.member;

import com.iko.iko.controller.member.dto.request.UpdateInfoRequestDto;

import java.util.List;

public interface MemberRepositoryCustom {
   Long updateInfo(UpdateInfoRequestDto requestDto);
}
