package com.flearndriving.management.application.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.flearndriving.management.application.entities.Chapter;

@Repository
public interface ChapterRespository extends JpaRepository<Chapter, Long>, PagingAndSortingRepository<Chapter, Long>,
        JpaSpecificationExecutor<Chapter> {

    @Query("SELECT c FROM Chapter c WHERE c.isDelete = false AND c.chapterId = :chapterId")
    public Chapter findByChapterId(Long chapterId);
    
    @Query("SELECT c FROM Chapter c WHERE c.isDelete = false")
    public List<Chapter> findAll();

    @Query("SELECT count(c) FROM Chapter c WHERE c.isDelete = false")
    Integer countChapter();
}
