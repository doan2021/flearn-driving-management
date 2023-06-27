package com.flearndriving.management.application.services.impl;

import com.flearndriving.management.application.common.Constant;
import com.flearndriving.management.application.converter.ChapterConverter;
import com.flearndriving.management.application.dto.request.ChapterRequest;
import com.flearndriving.management.application.dto.request.FormSearchChapter;
import com.flearndriving.management.application.entities.Chapter;
import com.flearndriving.management.application.entities.Chapter_;
import com.flearndriving.management.application.exception.BusinessException;
import com.flearndriving.management.application.respositories.ChapterRepository;
import com.flearndriving.management.application.respositories.DocumentRepository;
import com.flearndriving.management.application.services.ChapterServices;
import com.flearndriving.management.application.specification.ChapterSpecification;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class ChapterServicesImpl implements ChapterServices {

    @Resource
    ChapterRepository chapterRepository;

    @Resource
    DocumentRepository documentRepository;

    @Resource
    private ChapterConverter chapterConverter;

    public Chapter getChapterDetail(Long chapterId) {
        Chapter chapter = chapterRepository.findByChapterId(chapterId);
        if (chapter == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Chương không tồn tại!");
        }
        return chapter;
    }

    public List<Chapter> findAllChapter() {
        return chapterRepository.findAll(
                Sort.by(Sort.Direction.ASC, Chapter_.NAME));
    }

    @Transactional
    public void createChapter(ChapterRequest form) {
        if (Objects.isNull(form)) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Data error!");
        }
        chapterRepository.save(chapterConverter.buildChapter(form));
    }

    @Transactional
    public void updateChapter(ChapterRequest form) {
        Chapter chapter = chapterRepository.findByChapterId(form.getId());
        documentRepository.deleteAll(chapter.getListImages());
        chapterConverter.bukChapter(chapter, form);
        chapterRepository.save(chapter);
    }

    public Page<Chapter> searchChapter(FormSearchChapter formSearchChapter) {
        if (Objects.nonNull(formSearchChapter)) {
            if (formSearchChapter.getPageNumber() == null) {
                formSearchChapter.setPageNumber(0);
            }
            Specification<Chapter> conditions = Specification.where(ChapterSpecification.isDelete(false));
            if (StringUtils.isNotBlank(formSearchChapter.getIndex())) {
                conditions = conditions.and(ChapterSpecification.hasIndex(formSearchChapter.getName()));
            }
            if (StringUtils.isNotBlank(formSearchChapter.getName())) {
                conditions = conditions.and(ChapterSpecification.likeContent(formSearchChapter.getName()));
            }

            if (StringUtils.isNotBlank(formSearchChapter.getUpdateAtFrom())) {
                conditions = conditions.and(ChapterSpecification.hasUpdateAtFrom(formSearchChapter.getUpdateAtFrom()));
            }
            if (StringUtils.isNotBlank(formSearchChapter.getUpdateAtTo())) {
                conditions = conditions.and(ChapterSpecification.hasUpdateAtTo(formSearchChapter.getUpdateAtTo()));
            }
            Pageable pageable = PageRequest.of(formSearchChapter.getPageNumber(), Constant.RECORD_PER_PAGE);
            return chapterRepository.findAll(conditions, pageable);
        }
        return null;
    }

    public Object getObjectUpdate(Long chapterId) {
        Chapter chapter = chapterRepository.findByChapterId(chapterId);
        return chapterConverter.buildChapterResponse(chapter);
    }

    public Integer countChapter() {
        return chapterRepository.countChapter();
    }

    @Transactional
    public void deleteChapter(Long chapterId) {
        Chapter chapter = chapterRepository.findByChapterId(chapterId);
        chapter.setDelete(true);
        chapterRepository.save(chapter);
    }
}
