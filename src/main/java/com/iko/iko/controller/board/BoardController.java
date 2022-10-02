package com.iko.iko.controller.board;
import com.iko.iko.common.response.Response;
import com.iko.iko.controller.board.dto.BoardResponse;
import com.iko.iko.service.board.facade.BoardFacade;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardFacade boardFacade;

    @PostMapping("/main")
    @ApiOperation(value="공지사항 목록 필터링", notes="type 1:필독 2:배송 3:취소/교환/반품 4.결제 5.제품 6.회원")
    public ResponseEntity<Response<List<BoardResponse.BoardMain>>>
    getBoardMain(
            @RequestParam(value="type") Integer boardType,
            @RequestParam(value="page") Integer page,
            @PageableDefault(size=10, page=0)Pageable pageable
            ){
        return ResponseEntity.ok(
                Response.of(
                        boardFacade.getMainBoard(pageable,boardType),
                        "게시판 리스트 불러오기 완료"
                )
        );
    }

    @GetMapping("/details")
    public ResponseEntity<Response<List<BoardResponse.BoardDetails>>>
    getBoardDetails(
            @RequestParam(value="boardId") Integer selectedBoardId
    ){
        return ResponseEntity.ok(
                Response.of(
                        boardFacade.getBoardDetails(selectedBoardId),
                        "공지사항 상세정보 불러오기 완료"
                )
        );
    }

}
