package com.iko.iko.service.reply;

import com.iko.iko.controller.reply.dto.response.ReplyResponseDtO;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.domain.repository.reply.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GetReplyForProductDetailsService {
    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;

    public List<ReplyResponseDtO.ReplyInfoForProductDetails> getReplyForProductDetails(Pageable pageable, Integer productId) {
        List<ReplyResponseDtO.ReplyInfoForProductDetails> result =
                new ArrayList<>();

        Page<ReplyResponseDtO.ReplyInfoForMain> subResult
                = replyRepository.getReplyForProductDetails(pageable, productId);

        for (ReplyResponseDtO.ReplyInfoForMain rs : subResult) {
            String email = memberRepository.getEmailByMemberId(rs.getMemberId());
            ReplyResponseDtO.ReplyInfoForProductDetails a = new ReplyResponseDtO.ReplyInfoForProductDetails(
                    rs.getImageUrl(),
                    rs.getProductName(),
                    email,
                    rs.getRating(),
                    rs.getContent(),
                    rs.getCreatedAt()
            );
            result.add(a);
        }
        return result;
    }
}
