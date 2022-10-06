package com.iko.iko.service.reply;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.entity.Reply;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.domain.repository.reply.ReplyRepository;
import com.iko.iko.security.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.iko.iko.controller.reply.dto.request.ReplyRequestDto.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DeleteReplyService {

    private final MemberRepository memberRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public String deleteReply(DeleteReplyRequest deleteReplyRequest) {
        Member member = validateLoginStatus();
        Optional<Reply> reply = replyRepository.findById(deleteReplyRequest.getReplyId());
        if (reply.isPresent() && member.getMemberId().equals(reply.get().getMemberId())) {
            replyRepository.deleteReply(deleteReplyRequest.getReplyId());
        } else throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
        return "Ok";
    }

    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }
}
