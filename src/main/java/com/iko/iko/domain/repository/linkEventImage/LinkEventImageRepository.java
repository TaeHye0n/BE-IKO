package com.iko.iko.domain.repository.linkEventImage;

import com.iko.iko.domain.entity.LinkEventImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkEventImageRepository extends JpaRepository<LinkEventImage, Integer>, LinkEventImageRepositoryCustom {
}
