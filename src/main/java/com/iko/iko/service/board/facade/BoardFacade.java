package com.iko.iko.service.board.facade;

import com.iko.iko.controller.board.dto.BoardRequest;
import com.iko.iko.controller.board.dto.BoardResponse;
import com.iko.iko.service.board.AddBoardService;
import com.iko.iko.service.board.GetBoardDetailsService;
import com.iko.iko.service.board.GetBoardMainForAdminService;
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
    private final AddBoardService addBoardService;
    private final GetBoardMainForAdminService getBoardMainForAdminService;
    @Transactional(readOnly = true)
    public BoardResponse.BoardMainResponse
    getMainBoard(Integer bType){

        return getMainBoardService.GetMain(bType);
    }

    @Transactional(readOnly = true)
    public List<BoardResponse.BoardDetails>
    getBoardDetails(Integer selectedBoardId){
        return getBoardDetailsService.GetDetails(selectedBoardId);
    }

    @Transactional
    public String addBoard(BoardRequest.AddBoardRequest request){
        return addBoardService.addBoard(request);
    }

    @Transactional(readOnly = true)
    public BoardResponse.BoardMainForAdminResponse getBoardMainForAdmin(){
        return getBoardMainForAdminService.GetBoardMainForAdmin();
    }

}
