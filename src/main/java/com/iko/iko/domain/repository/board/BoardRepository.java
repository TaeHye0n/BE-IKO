package com.iko.iko.domain.repository.board;
import com.iko.iko.domain.entity.Board;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer>, BoardRepositoryCustom {
}
