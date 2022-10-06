package com.iko.iko.service.reply;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.domain.repository.reply.ReplyRepository;
import com.iko.iko.controller.reply.dto.response.ReplyResponseDtO.*;
import com.iko.iko.security.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MyReplyListService {

    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<MyReplyInfoResponse> myReplyList() {
        Member member = validateLoginStatus();
        List<MyReplyInfoResponse> result = new ArrayList<>();
        List<MyReplyAndIdsResponse> myReplyAndIdsResponseList
                = replyRepository.getMyReplyAndIdsInfo(member.getMemberId());
        for (MyReplyAndIdsResponse myReplyAndIdsResponse : myReplyAndIdsResponseList) {
            List<ProductInfoForReplyResponse> productInfoForReplyResponseList
                    = replyRepository.getProductInfoForReply(myReplyAndIdsResponse.getOrderId(), myReplyAndIdsResponse.getProductDetailsId());
            MyReplyInfoResponse myReplyInfoResponse = new MyReplyInfoResponse(myReplyAndIdsResponse, productInfoForReplyResponseList);
            result.add(myReplyInfoResponse);
        }
        return result;
    }

    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }
}
