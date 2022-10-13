package com.iko.iko.service.reply.facade;

import com.iko.iko.controller.reply.dto.request.ReplyRequestDto.*;
import com.iko.iko.controller.reply.dto.response.ReplyResponseDtO;
import com.iko.iko.controller.reply.dto.response.ReplyResponseDtO.*;
import com.iko.iko.service.reply.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
    private final GetReplyDataService getReplayDataService;
    private final GetProductIdForReply getProductIdForReply;
    private final GetReplyForProductDetailsService getReplyForProductDetailsService;
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

    @Transactional(readOnly = true)
    public ReplyResponseDtO.ReplyInfoForResponse getReplyInfoByProductId(Integer productId){
        return getReplayDataService.getReplyData(productId);
    }

    @Transactional(readOnly = true)
    public List<ReplyResponseDtO.ReplyInfoForProductDetails> getReplyForProductDetails(Pageable pageable,Integer productId){
        return getReplyForProductDetailsService.getReplyForProductDetails(pageable,productId);
    }

    @Transactional(readOnly = true)
    public List<ReplyResponseDtO.ReplyForProduct> getProductIdForReply(){
        return getProductIdForReply.getProductIdForReply();
    }

}
