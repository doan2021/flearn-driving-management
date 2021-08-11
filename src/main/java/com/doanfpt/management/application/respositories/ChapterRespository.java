package com.doanfpt.management.application.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.doanfpt.management.application.entities.Chapter;

@Repository
public interface ChapterRespository extends JpaRepository<Chapter, Long>, PagingAndSortingRepository<Chapter, Long>,
        JpaSpecificationExecutor<Chapter> {

    public Chapter findByChapterId(Long chapterId);

    @Query("SELECT count(c) FROM Chapter c")
    Integer countChapter();
}
