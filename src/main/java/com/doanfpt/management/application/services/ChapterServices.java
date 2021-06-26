package com.doanfpt.management.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.doanfpt.management.application.common.Constant;
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

    public void saveChapter(Chapter chapter) {
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
            if (StringUtils.isEmptyOrWhitespace(formSearchChapter.getName())) {
                conditions = conditions.and(ChapterSpecification.hasName(formSearchChapter.getName()));
            }
            if (StringUtils.isEmptyOrWhitespace(formSearchChapter.getContent())) {
                conditions = conditions.and(ChapterSpecification.likeContent(formSearchChapter.getContent()));
            }

            if (StringUtils.isEmptyOrWhitespace(formSearchChapter.getUpdateAtFrom())) {
                conditions = conditions.and(ChapterSpecification.hasUpdateAtFrom(formSearchChapter.getUpdateAtFrom()));
            }
            if (StringUtils.isEmptyOrWhitespace(formSearchChapter.getUpdateAtTo())) {
                conditions = conditions.and(ChapterSpecification.hasUpdateAtTo(formSearchChapter.getUpdateAtTo()));
            }
        }

        Pageable pageable = PageRequest.of(formSearchChapter.getPageNumber(), Constant.RECORD_PER_PAGE);
        Page<Chapter> listChapter = chapterResponsitory.findAll(conditions, pageable);
        return listChapter;
    }
}
