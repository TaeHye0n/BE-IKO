package com.iko.iko.domain.repository.member;

import com.iko.iko.controller.member.dto.request.UpdateInfoRequestDto;
import com.iko.iko.controller.member.dto.response.MyOrderListResponseDto;


import java.util.List;

public interface MemberRepositoryCustom {
   Long updateInfo(UpdateInfoRequestDto requestDto);
   
//   List<MyOrderListResponseDto> myOrderList(Integer memberId);

}
