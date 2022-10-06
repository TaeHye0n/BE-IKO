package com.iko.iko.service.reply.facade;

import com.iko.iko.controller.reply.dto.request.ReplyRequestDto.*;
import com.iko.iko.controller.reply.dto.response.ReplyResponseDtO.*;
import com.iko.iko.service.reply.AddReplyService;
import com.iko.iko.service.reply.DeleteReplyService;
import com.iko.iko.service.reply.MyReplyListService;
import com.iko.iko.service.reply.UpdateReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyFacade {

    private final AddReplyService addReplyService;
    private final DeleteReplyService deleteReplyService;
    private final UpdateReplyService updateReplyService;
    private final MyReplyListService myReplyListService;

    @Transactional
    public String addReply(AddReplyRequest addReplyRequest){
        return addReplyService.addReply(addReplyRequest);
    }

    @Transactional
    public String deleteReply(DeleteReplyRequest deleteReplyRequest){
        return deleteReplyService.deleteReply(deleteReplyRequest);
    }

    @Transactional
    public String updateReply(UpdateReplyRequest updateReplyRequest){
        return updateReplyService.updateReply(updateReplyRequest);
    }

    @Transactional(readOnly = true)
    public List<MyReplyInfoResponse> myReplyList(){
        return myReplyListService.myReplyList();
    }


}
