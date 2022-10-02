package com.iko.iko.domain.repository.favor;

import com.iko.iko.domain.entity.Favor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavorRepository extends JpaRepository<Favor, Integer>, FavorRepositoryCustom {

}
