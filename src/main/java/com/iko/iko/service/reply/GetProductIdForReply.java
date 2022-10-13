package com.iko.iko.service.reply;

import com.iko.iko.controller.reply.dto.response.ReplyResponseDtO;
import com.iko.iko.domain.repository.reply.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetProductIdForReply {
    private final ReplyRepository replyRepository;

    @Transactional(readOnly = true)
    public List<ReplyResponseDtO.ReplyForProduct> getProductIdForReply(){
        return replyRepository.getProductIdForReply();
    }
}
