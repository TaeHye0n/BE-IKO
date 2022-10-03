package com.iko.iko.domain.repository.image;

import com.iko.iko.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer>, ImageRepositoryCustom {
}