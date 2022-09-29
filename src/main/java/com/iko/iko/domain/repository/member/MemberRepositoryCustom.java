package com.iko.iko.domain.repository.member;

import com.iko.iko.controller.member.dto.request.UpdateInfoRequestDto;
import com.iko.iko.controller.member.dto.response.MyOrderListResponseDto;
import com.iko.iko.domain.entity.Member;


import java.util.List;

public interface MemberRepositoryCustom {
   Long updateInfo(UpdateInfoRequestDto requestDto);

   List<MyOrderListResponseDto> MyOrderList(Member member);

   Long logout(Member member);

  // Long orderCancel(Integer orderId);
}
