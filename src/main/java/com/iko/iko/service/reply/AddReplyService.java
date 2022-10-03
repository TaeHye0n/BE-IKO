package com.iko.iko.service.reply;

import com.iko.iko.common.exception.BaseException;
import com.iko.iko.common.response.ErrorCode;
import com.iko.iko.controller.reply.dto.request.ReplyRequestDto.AddReplyRequest;
import com.iko.iko.domain.entity.LinkOrderDetails;
import com.iko.iko.domain.entity.Order;
import com.iko.iko.domain.entity.Reply;
import com.iko.iko.domain.repository.linkOrderDetails.LinkOrderDetailsRepository;
import com.iko.iko.domain.repository.order.OrderRepository;
import com.iko.iko.domain.repository.reply.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class AddReplyService {

    private final ReplyRepository replyRepository;
    private final OrderRepository orderRepository;

    private final LinkOrderDetailsRepository linkOrderDetailsRepository;

    @Transactional
    public String addReply(AddReplyRequest addReplyRequest){
        Optional<Order> order = orderRepository.findById(addReplyRequest.getOrderId());
        if(order.isPresent() && order.get().getMemberId().equals(addReplyRequest.getMemberId())){
                Optional<LinkOrderDetails> linkOrderDetails = linkOrderDetailsRepository.findByOrderId(addReplyRequest.getOrderId());
                if(linkOrderDetails.isPresent() && linkOrderDetails.get().getProductDetailsId().equals(addReplyRequest.getProductDetailsId())){
                    Reply reply = replyRepository.save(addReplyRequest.toEntity());
                }
                else throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);
        }
        else throw new BaseException(ErrorCode.COMMON_BAD_REQUEST);

        return "Ok";
    }
}

