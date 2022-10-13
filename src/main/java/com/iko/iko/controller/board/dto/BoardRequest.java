package com.iko.iko.controller.board.dto;

import com.iko.iko.domain.entity.Board;
import com.iko.iko.domain.entity.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class BoardRequest {
    @Getter
    @AllArgsConstructor
    @Builder
    @Setter
    public static class AddBoardRequest{
        private Integer boardType;
        private String boardTitle;
        private String boardContent;

        @Builder
        public Board toEntity() {
            return Board.builder()
                    .boardType(boardType)
                    .boardTitle(boardTitle)
                    .boardDescription(boardContent)
                    .build();
        }
    }
}
