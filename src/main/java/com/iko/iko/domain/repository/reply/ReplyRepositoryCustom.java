package com.iko.iko.domain.repository.reply;

import com.iko.iko.controller.reply.dto.request.ReplyRequestDto.UpdateReplyRequest;
import com.iko.iko.controller.reply.dto.response.ReplyResponseDtO;
import com.iko.iko.controller.reply.dto.response.ReplyResponseDtO.*;
import com.iko.iko.domain.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ReplyRepositoryCustom {

    Long deleteReply(Integer replyId);

    Long deleteReplyForOrder(Integer orderId);

    Long updateReply(UpdateReplyRequest updateReplyRequest);

    List<MyReplyAndIdsResponse> getMyReplyAndIdsInfo(Integer memberId);

    List<ProductInfoForReplyResponse> getProductInfoForReply(Integer orderId, Integer productDetailsId);

    List<Reply> getReplyList(Integer orderId, Integer productDetailsId);

    List<ReplyResponseDtO.ReplyData> getReplyData(Integer productId);

    Page<ReplyInfoForMain> getReplyForProductDetails(Pageable pageable,Integer productId);

    List<ReplyResponseDtO.ReplyForProduct> getProductIdForReply();

    Page<ReplyInfoByName> ReplyInfoByName(Pageable pageable, Integer productId);
}
