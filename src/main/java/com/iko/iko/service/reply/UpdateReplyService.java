package com.iko.iko.service.reply;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.domain.entity.Member;
import com.iko.iko.domain.entity.Reply;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.domain.repository.reply.ReplyRepository;
import com.iko.iko.controller.reply.dto.request.ReplyRequestDto.UpdateReplyRequest;
import com.iko.iko.security.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UpdateReplyService {

    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public String updateReply(UpdateReplyRequest updateReplyRequest) {
        Optional<Reply> reply = replyRepository.findById(updateReplyRequest.getReplyId());
        Member member = validateLoginStatus();
        if (reply.isPresent() && reply.get().getMemberId().equals(member.getMemberId())) {
            replyRepository.updateReply(updateReplyRequest);
        } else throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
        return "Ok";
    }

    public Member validateLoginStatus() {
        return memberRepository.findByEmail(SecurityUtil.getLoginUserEmail())
                .orElseThrow(() -> new BaseException(ErrorCode.NEED_LOGIN));
    }
}
