package com.iko.iko.service.board.facade;

import com.iko.iko.controller.board.dto.BoardResponse;
import com.iko.iko.service.board.GetBoardDetailsService;
import com.iko.iko.service.board.GetMainBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
public class BoardFacade {
    private final GetMainBoardService getMainBoardService;
    private final GetBoardDetailsService getBoardDetailsService;
    @Transactional(readOnly = true)
    public List<BoardResponse.BoardMain>
    getMainBoard(Pageable pageable){
        return getMainBoardService.GetMain(pageable);
    }

    @Transactional(readOnly = true)
    public List<BoardResponse.BoardDetails>
    getBoardDetails(Integer selectedBoardId){
        return getBoardDetailsService.GetDetails(selectedBoardId);
    }
}
