package com.iko.iko.service.reply;

import com.iko.iko.controller.reply.dto.response.ReplyResponseDtO;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.domain.repository.product.ProductRepository;
import com.iko.iko.domain.repository.reply.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AllReplyService {

    private final ReplyRepository replyRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<ReplyResponseDtO.ReplyInfoByNameResponse> AllReply(Pageable pageable) {
        List<ReplyResponseDtO.ReplyInfoByNameResponse> result = new ArrayList<>();
        List<Integer> productIdList = productRepository.getAllProductId();
        for (Integer productId : productIdList) {
            Page<ReplyResponseDtO.ReplyInfoByName> replyInfoByNamePage = replyRepository.ReplyInfoByName(pageable, productId);

            for (ReplyResponseDtO.ReplyInfoByName replyInfoByName : replyInfoByNamePage) {
                String email = memberRepository.getEmailByMemberId(replyInfoByName.getMemberId());
                ReplyResponseDtO.ReplyInfoByNameResponse replyInfoByNameResponse = new ReplyResponseDtO.ReplyInfoByNameResponse(
                        replyInfoByName.getReviewImageUrl(),
                        replyInfoByName.getGraphicDiameter(),
                        replyInfoByName.getPeriod(),
                        replyInfoByName.getProductImageUrl(),
                        replyInfoByName.getProductName(),
                        replyInfoByName.getColor(),
                        email,
                        replyInfoByName.getRating(),
                        replyInfoByName.getContent(),
                        replyInfoByName.getCreatedAt()
                );
                result.add(replyInfoByNameResponse);
            }
        }
        return result;
    }

}
