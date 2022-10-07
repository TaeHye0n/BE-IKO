package com.iko.iko.service.reply;

import com.iko.iko.controller.reply.dto.response.ReplyResponseDtO;
import com.iko.iko.domain.repository.member.MemberRepository;
import com.iko.iko.domain.repository.reply.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class GetReplyDataService {
    private final ReplyRepository replyRepository;
    private final MemberRepository memberRepository;

    public ReplyResponseDtO.ReplyInfoForResponse getReplyData(Integer productId){
        List<ReplyResponseDtO.ReplyData> replyDataList= replyRepository.getReplyData(productId);

        List<ReplyResponseDtO.ReplyDataResponse> replyDataResponse = new ArrayList<>();
        for(ReplyResponseDtO.ReplyData replyResult : replyDataList){
            String email = memberRepository.getEmailByMemberId(replyResult.getMemberId());
            ReplyResponseDtO.ReplyDataResponse checkData=new ReplyResponseDtO.ReplyDataResponse(
                    replyResult.getRating(),
                    email,
                    replyResult.getColor(),
                    replyResult.getGraphicDiameter(),
                    replyResult.getPeriod(),
                    replyResult.getContent(),
                    replyResult.getImageUrl(),
                    replyResult.getCreatedAt()
            );
            replyDataResponse.add(checkData);

        }
        ReplyResponseDtO.ReplyInfoForResponse result=new ReplyResponseDtO.ReplyInfoForResponse(
                replyDataResponse.size(),
                replyDataResponse
        );
        return result;
    }
}
