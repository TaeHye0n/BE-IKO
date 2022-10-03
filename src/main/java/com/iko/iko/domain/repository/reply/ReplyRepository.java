package com.iko.iko.domain.repository.reply;

import com.iko.iko.domain.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Integer>, ReplyRepositoryCustom {
}
