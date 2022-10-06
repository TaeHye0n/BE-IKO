package com.iko.iko.domain.repository.reply;

import com.iko.iko.controller.reply.dto.request.ReplyRequestDto.UpdateReplyRequest;
import com.iko.iko.controller.reply.dto.response.ReplyResponseDtO.*;

import java.util.List;


public interface ReplyRepositoryCustom {

    Long deleteReply(Integer replyId);

    Long updateReply(UpdateReplyRequest updateReplyRequest);

    List<MyReplyAndIdsResponse> getMyReplyAndIdsInfo(Integer memberId);

    List<ProductInfoForReplyResponse> getProductInfoForReply(Integer orderId, Integer productDetailsId);
}
