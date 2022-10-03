package com.iko.iko.service.reply.facade;

import com.iko.iko.controller.reply.dto.request.ReplyRequestDto.AddReplyRequest;
import com.iko.iko.service.reply.AddReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReplyFacade {

    private final AddReplyService addReplyService;

    @Transactional
    public String addReply(AddReplyRequest addReplyRequest){
        return addReplyService.addReply(addReplyRequest);
    }
}
