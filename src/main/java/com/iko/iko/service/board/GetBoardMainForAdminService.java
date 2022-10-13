package com.iko.iko.service.board;

import com.iko.iko.controller.board.dto.BoardResponse;
import com.iko.iko.domain.repository.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetBoardMainForAdminService {
    private final BoardRepository boardRepository;

    public BoardResponse.BoardMainForAdminResponse GetBoardMainForAdmin(){
        List<BoardResponse.BoardMainForAdmin> boardList = boardRepository.getMainForAdmin();
        Integer totalCount= boardList.size();

        BoardResponse.BoardMainForAdminResponse result =
               new BoardResponse.BoardMainForAdminResponse(
                       totalCount,
                       boardList
               );
        return result;
    }
}
