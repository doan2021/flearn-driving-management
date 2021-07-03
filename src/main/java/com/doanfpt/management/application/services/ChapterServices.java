package com.doanfpt.management.application.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.doanfpt.management.application.common.Common;
import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.dto.ChapterForm;
import com.doanfpt.management.application.dto.FormSearchChapter;
import com.doanfpt.management.application.entities.Chapter;
import com.doanfpt.management.application.responsitories.ChapterResponsitory;
import com.doanfpt.management.application.specification.ChapterSpecification;

@Service
public class ChapterServices {

    @Autowired
    ChapterResponsitory chapterResponsitory;

    public Chapter getChapterDetail(Long chapterId) {
        return chapterResponsitory.findByChapterIdAndIsDelete(chapterId, false);
    }

    public void saveChapter(ChapterForm chapterForm) {
    	Chapter chapter = new Chapter();
    	chapter.setName(chapterForm.getName());
    	chapter.setDescription(chapterForm.getDescription());
    	chapter.setContent(chapterForm.getContent());
    	chapter.setUpdateAt(Common.getSystemDate());
        chapterResponsitory.save(chapter);
    }

    public Page<Chapter> getAllChapter(Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = 0;
        }
        Specification<Chapter> conditions = Specification.where(ChapterSpecification.isDelete(false));
        Pageable pageable = PageRequest.of(pageNumber, Constant.RECORD_PER_PAGE);
        Page<Chapter> listChapter = chapterResponsitory.findAll(conditions, pageable);
        return listChapter;
    }

    public Page<Chapter> searchChapter(FormSearchChapter formSearchChapter) {
        if (formSearchChapter.getPageNumber() == null) {
            formSearchChapter.setPageNumber(0);
        }
        // Init condition with is_delete
        Specification<Chapter> conditions = Specification.where(ChapterSpecification.isDelete(false));
        if (formSearchChapter != null) {
            if (StringUtils.isNotBlank(formSearchChapter.getName())) {
                conditions = conditions.and(ChapterSpecification.hasName(formSearchChapter.getName()));
            }
            if (StringUtils.isNotBlank(formSearchChapter.getContent())) {
                conditions = conditions.and(ChapterSpecification.likeContent(formSearchChapter.getContent()));
            }

            if (StringUtils.isNotBlank(formSearchChapter.getUpdateAtFrom())) {
                conditions = conditions.and(ChapterSpecification.hasUpdateAtFrom(formSearchChapter.getUpdateAtFrom()));
            }
            if (StringUtils.isNotBlank(formSearchChapter.getUpdateAtTo())) {
                conditions = conditions.and(ChapterSpecification.hasUpdateAtTo(formSearchChapter.getUpdateAtTo()));
            }
        }

        Pageable pageable = PageRequest.of(formSearchChapter.getPageNumber(), Constant.RECORD_PER_PAGE);
        Page<Chapter> listChapter = chapterResponsitory.findAll(conditions, pageable);
        return listChapter;
    }
}
