package com.iko.iko.service.board;

import com.iko.iko.controller.board.dto.BoardRequest;
import com.iko.iko.domain.entity.Board;
import com.iko.iko.domain.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AddBoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public String addBoard(BoardRequest.AddBoardRequest request){
        Board board=boardRepository.save(request.toEntity());
        return "Ok";
    }
}
