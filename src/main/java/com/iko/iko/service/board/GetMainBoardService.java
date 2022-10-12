package com.iko.iko.service.board;
import com.iko.iko.controller.board.dto.BoardResponse;
import com.iko.iko.domain.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import  java.util.List;
@Service
@RequiredArgsConstructor
public class GetMainBoardService {
    private final BoardRepository boardRepository;

    public List<BoardResponse.BoardMain> GetMain(
            Integer bType){
        return boardRepository.getMain(bType);
    }

}
