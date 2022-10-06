package com.iko.iko.controller.reply;

import com.iko.iko.common.response.Response;
import com.iko.iko.controller.reply.dto.request.ReplyRequestDto.*;
import com.iko.iko.controller.reply.dto.response.ReplyResponseDtO.*;
import com.iko.iko.service.reply.facade.ReplyFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("reply")
public class ReplyController {

    private final ReplyFacade replyFacade;
    @PostMapping("/add")
    public ResponseEntity<Response<String>> addReply(
            @RequestBody @Valid AddReplyRequest addReplyRequest
    ) {
        return ResponseEntity.ok(
                Response.of(replyFacade.addReply(addReplyRequest),
                        "리뷰 작성 완료")
        );
    }

    @PostMapping("/delete")
    public ResponseEntity<Response<String>> deleteReply(
            @RequestBody @Valid DeleteReplyRequest deleteReplyRequest
    ){
        return ResponseEntity.ok(
                Response.of(replyFacade.deleteReply(deleteReplyRequest),
                        "리뷰 삭제 완료")
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Response<String>> updateReply(
            @RequestBody @Valid UpdateReplyRequest updateReplyRequest
    ){
        return ResponseEntity.ok(
                Response.of(replyFacade.updateReply(updateReplyRequest),
                        "리뷰 수정 완료")
        );
    }

    @GetMapping("/myReply")
    public ResponseEntity<Response<List<MyReplyInfoResponse>>> myReplyList(){
        return ResponseEntity.ok(
                Response.of(replyFacade.myReplyList(),
                        "나의 리뷰 정보 조회 완료")
        );
    }
}
