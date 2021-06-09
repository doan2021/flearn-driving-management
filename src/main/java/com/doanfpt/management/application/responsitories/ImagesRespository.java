package com.doanfpt.management.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doanfpt.management.application.entities.Image;

@Repository
public interface ImagesRespository  extends JpaRepository<Image, Long> {

}
