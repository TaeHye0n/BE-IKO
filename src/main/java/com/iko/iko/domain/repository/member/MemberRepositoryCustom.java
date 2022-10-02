package com.iko.iko.domain.repository.member;

import com.iko.iko.controller.member.dto.request.UpdateInfoRequestDto;
import com.iko.iko.controller.member.dto.response.MyOrderListResponseDto;
import com.iko.iko.controller.member.dto.response.MyReplyListResponseDto;
import com.iko.iko.domain.entity.Member;


import java.util.List;

public interface MemberRepositoryCustom {
   Long updateInfo(UpdateInfoRequestDto requestDto, Member member);

   List<MyOrderListResponseDto> myOrderList(Member member);

   Long logout(Member member);

   Long deleteLinkOrder(Integer orderId);

   Long deleteOrder(Member member, Integer orderId);

   List<MyReplyListResponseDto> myReplyList(Member member);

   Long addPoint(Integer memberId, Integer point);

}
