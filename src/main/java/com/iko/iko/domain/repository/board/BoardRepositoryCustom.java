package com.iko.iko.domain.repository.board;
import com.iko.iko.controller.board.dto.BoardResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface BoardRepositoryCustom {

    List<BoardResponse.BoardMain> getMain(Pageable pageable);

    List<BoardResponse.BoardDetails> getDetails(Integer selectedBoardId);
}
