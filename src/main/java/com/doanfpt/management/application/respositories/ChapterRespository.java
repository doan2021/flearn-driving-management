package com.doanfpt.management.application.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.doanfpt.management.application.entities.Chapter;

@Repository
public interface ChapterRespository  extends JpaRepository<Chapter, Long>, PagingAndSortingRepository<Chapter, Long>, JpaSpecificationExecutor<Chapter> {

    public Chapter findByChapterIdAndIsDelete(Long chapterId, Boolean isDelete);
    
    public List<Chapter> findByIsDeleteOrderByName(Boolean isDelete);
}
