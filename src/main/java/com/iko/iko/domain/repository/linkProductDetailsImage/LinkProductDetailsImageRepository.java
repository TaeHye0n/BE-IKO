package com.iko.iko.domain.repository.linkProductDetailsImage;

import com.iko.iko.domain.entity.LinkProductDetailsImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkProductDetailsImageRepository extends JpaRepository
        <LinkProductDetailsImage, Integer>, LinkProductDetailsImageRepositoryCustom {
}
