package com.doanfpt.management.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.doanfpt.management.application.entities.Chapter;

@Repository
public interface ChapterResponsitory  extends JpaRepository<Chapter, Long>, PagingAndSortingRepository<Chapter, Long>, JpaSpecificationExecutor<Chapter> {
	
}
