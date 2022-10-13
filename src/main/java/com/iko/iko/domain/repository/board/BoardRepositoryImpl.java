package com.iko.iko.domain.repository.board;

import com.iko.iko.controller.board.dto.BoardResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;
import static com.iko.iko.domain.entity.QBoard.board;
@Repository
@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BoardResponse.BoardMain> getMain(Integer bType){
        return jpaQueryFactory
                .select(Projections.constructor(BoardResponse.BoardMain.class,
                        board.createdAt,
                        board.boardId,
                        board.boardTitle,
                        board.boardType))
                .from(board)
                .where(board.boardType.eq(bType))
                .fetch();
    }

    @Override
    public List<BoardResponse.BoardDetails> getDetails(Integer selectedBoardId){
        return jpaQueryFactory
                .select(Projections.constructor(BoardResponse.BoardDetails.class,
                        board.boardTitle,
                        board.createdBy,
                        board.createdAt,
                        board.boardDescription))
                .from(board)
                .where(board.boardId.eq(selectedBoardId))
                .fetch();
    }

    @Override
    public List<BoardResponse.BoardMainForAdmin> getMainForAdmin(){
        return jpaQueryFactory
                .select(Projections.constructor(BoardResponse.BoardMainForAdmin.class,
                        board.boardType,
                        board.boardTitle,
                        board.boardId,
                        board.createdAt))
                .from(board)
                .distinct()
                .fetch();
    }

}
