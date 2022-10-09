package com.iko.iko.domain.repository.favor;

import com.iko.iko.domain.entity.Favor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface FavorRepository extends JpaRepository<Favor, Integer>, FavorRepositoryCustom {
    @Query(value = "SELECT created_at FROM tb_favor WHERE member_id_fk = :memberId", nativeQuery = true)
    List<Timestamp> getFavorCreatedAt(Integer memberId);

}
