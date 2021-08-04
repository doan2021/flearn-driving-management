package com.doanfpt.management.application.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
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
import com.doanfpt.management.application.respositories.ChapterRespository;
import com.doanfpt.management.application.specification.ChapterSpecification;

@Service
public class ChapterServices {

    @Autowired
    ChapterRespository chapterResponsitory;

    public Chapter getChapterDetail(Long chapterId) {
        return chapterResponsitory.findByChapterIdAndIsDelete(chapterId, Constant.IS_NOT_DELETE);
    }
    
    public List<Chapter> findAllChapter() {
        return chapterResponsitory.findByIsDeleteOrderByName(Constant.IS_NOT_DELETE);
    }

    public boolean saveChapter(ChapterForm chapterForm) {
        if (chapterForm == null) {
            return false;
        }
        Chapter chapter = new Chapter();
        chapter.setName(chapterForm.getName());
        chapter.setDescription(chapterForm.getDescription());
        chapter.setContent(chapterForm.getContent());
        chapter.setCreateBy(Common.getUsernameLogin());
        chapter.setCreateAt(Common.getSystemDate());
        chapter.setUpdateBy(Common.getUsernameLogin());
        chapter.setUpdateAt(Common.getSystemDate());
        if (chapterResponsitory.save(chapter) == null) {
            return false;
        } else {
            return true;
        }
    }

    public void editChapterDetail(ChapterForm chapterForm) {
        Chapter chapter = chapterResponsitory.findByChapterIdAndIsDelete(chapterForm.getChapterId(), false);
        chapter.setName(chapterForm.getName());
        chapter.setContent(chapterForm.getContent());
        chapter.setDescription(chapterForm.getDescription());
        chapter.setUpdateBy(Common.getUsernameLogin());
        chapter.setUpdateAt(Common.getSystemDate());
        chapterResponsitory.save(chapter);
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

    public Object getObjectUpdate(Long chapterId) {
        ChapterForm chapterForm = new ChapterForm();
        Chapter chapter = chapterResponsitory.findByChapterIdAndIsDelete(chapterId, Constant.IS_NOT_DELETE);
        chapterForm.setName(chapter.getName());
        chapterForm.setChapterId(chapter.getChapterId());
        chapterForm.setDescription(chapter.getDescription());
        chapterForm.setContent(chapter.getContent());
        chapterForm.setUpdateAt(DateFormatUtils.format(chapter.getUpdateAt(), Constant.FORMAT_DATE_TIME));
        chapterForm.setUpdateBy(Common.getUsernameLogin());
        return chapterForm;
    }
    
    public List<Chapter> getListChapterForExamQuestion(String listChapterName) {
        return null;
    }
    
	public Integer countChapter() {
		return chapterResponsitory.countChapter();
	}
}
