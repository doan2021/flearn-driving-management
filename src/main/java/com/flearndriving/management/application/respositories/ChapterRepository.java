package com.flearndriving.management.application.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.flearndriving.management.application.entities.Chapter;

public interface ChapterRepository extends JpaRepository<Chapter, Long>, PagingAndSortingRepository<Chapter, Long>,
		JpaSpecificationExecutor<Chapter> {

	@Query("SELECT c FROM Chapter c WHERE c.isDelete = false AND c.id = :id")
	Chapter findByChapterId(Long id);

	@Query("SELECT c FROM Chapter c WHERE c.isDelete = false")
	List<Chapter> findAll();

	@Query("SELECT count(c) FROM Chapter c WHERE c.isDelete = false")
	Integer countChapter();
}
