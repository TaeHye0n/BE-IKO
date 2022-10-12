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

    @GetMapping("/main")
     public ResponseEntity<Response<BoardResponse.BoardMainResponse>>
    getBoardMain(
            @RequestParam(value="type") Integer boardType
            ){
        return ResponseEntity.ok(
                Response.of(
                        boardFacade.getMainBoard(boardType),
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
