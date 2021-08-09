package com.doanfpt.management.application.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.doanfpt.management.application.common.Common;
import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.common.MimeTypes;
import com.doanfpt.management.application.dto.ChapterForm;
import com.doanfpt.management.application.dto.FormSearchChapter;
import com.doanfpt.management.application.entities.Chapter;
import com.doanfpt.management.application.entities.Document;
import com.doanfpt.management.application.entities.Question;
import com.doanfpt.management.application.exception.BusinessException;
import com.doanfpt.management.application.respositories.ChapterRespository;
import com.doanfpt.management.application.respositories.DocumentRespository;
import com.doanfpt.management.application.respositories.QuestionsRespository;
import com.doanfpt.management.application.specification.ChapterSpecification;

@Service
public class ChapterServices {

    @Autowired
    ChapterRespository chapterResponsitory;

    @Autowired
    QuestionsRespository questionsRespository;

    @Autowired
    AmazonS3ClientService amazonS3ClientService;

    @Autowired
    DocumentRespository documentRespository;

    public Chapter getChapterDetail(Long chapterId) {
        Chapter chapter = chapterResponsitory.findByChapterIdAndIsDelete(chapterId, Constant.IS_NOT_DELETE);
        if (chapter == null) {
        	throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Chương không tồn tại!");
        }
        return chapter;
    }

    public List<Chapter> findAllChapter() {
        return chapterResponsitory.findByIsDeleteOrderByName(Constant.IS_NOT_DELETE);
    }

    @Transactional
    public void saveChapter(ChapterForm chapterForm) {
        if (chapterForm == null) {
        	throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Dữ liệu truyền vào không đúng!");
        }
        Chapter chapter = new Chapter();
        chapter.setName(chapterForm.getName());
        chapter.setDescription(chapterForm.getDescription());
        chapter.setContent(chapterForm.getContent());
        chapter.setCreateBy(Common.getUsernameLogin());
        chapter.setCreateAt(Common.getSystemDate());
        chapter.setUpdateBy(Common.getUsernameLogin());
        chapter.setUpdateAt(Common.getSystemDate());
        chapterResponsitory.save(chapter);
        // Handle document
        List<Document> listDocuments = new ArrayList<>();
        if (chapterForm.getImages() != null && !chapterForm.getImages()[0].isEmpty()) {
            // Get file to client
            for (MultipartFile multipartFile : chapterForm.getImages()) {
                Document document = new Document();
                document.setChapter(chapter);
                document.setFileName(Common.generateFileName(multipartFile, Constant.DOCUMENT_ORTHER_LABEL));
                document.setOriginFileName(multipartFile.getOriginalFilename());
                document.setExtension(MimeTypes.lookupExt(multipartFile.getContentType()));
                document.setContentType(multipartFile.getContentType());
                document.setSize(multipartFile.getSize());
                document.setType(Constant.TYPE_DOCUMENT_ORTHER);
                document.setDescription("Ảnh mô tả chương");
                document.setCreateAt(Common.getSystemDate());
                document.setCreateBy(Common.getUsernameLogin());
                document.setPath(amazonS3ClientService.uploadFileToS3Bucket(multipartFile, document.getFileName()));
                listDocuments.add(document);
            }
            documentRespository.saveAll(listDocuments);
        }
    }

    @Transactional
    public void updateChapter(ChapterForm chapterForm) {
        Chapter chapter = chapterResponsitory.findByChapterIdAndIsDelete(chapterForm.getChapterId(),
                Constant.IS_NOT_DELETE);
        chapter.setName(chapterForm.getName());
        chapter.setContent(chapterForm.getContent());
        chapter.setDescription(chapterForm.getDescription());
        chapter.setUpdateBy(Common.getUsernameLogin());
        chapter.setUpdateAt(Common.getSystemDate());
        // Handle document
        if (chapterForm.getImages() != null && !chapterForm.getImages()[0].isEmpty()) {
            documentRespository.deleteAll(chapter.getListImages());
            List<Document> listDocuments = new ArrayList<>();
            // Delete list old image
            for (MultipartFile multipartFile : chapterForm.getImages()) {
                Document document = new Document();
                document.setChapter(chapter);
                document.setFileName(Common.generateFileName(multipartFile, Constant.DOCUMENT_ORTHER_LABEL));
                document.setOriginFileName(multipartFile.getOriginalFilename());
                document.setExtension(MimeTypes.lookupExt(multipartFile.getContentType()));
                document.setContentType(multipartFile.getContentType());
                document.setSize(multipartFile.getSize());
                document.setType(Constant.TYPE_DOCUMENT_ORTHER);
                document.setDescription("Ảnh mô tả chương");
                document.setCreateAt(Common.getSystemDate());
                document.setCreateBy(Common.getUsernameLogin());
                document.setPath(amazonS3ClientService.uploadFileToS3Bucket(multipartFile, document.getFileName()));
                listDocuments.add(document);
            }
            documentRespository.saveAll(listDocuments);
            chapter.setListImages(listDocuments);
        }
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

    @Transactional
    public void deleteChapter(Long chapterId) {
        Chapter chapter = chapterResponsitory.findByChapterIdAndIsDelete(chapterId, Constant.IS_NOT_DELETE);
        if (chapter == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Chương không tồn tại!");
        }
        chapter.setDelete(Constant.IS_DELETE);
        chapter.setUpdateBy(Common.getUsernameLogin());
        chapter.setUpdateAt(Common.getSystemDate());
        List<Question> listQuestion = new ArrayList<>();
        for (Question question : chapter.getListQuestion()) {
            question.setDelete(Constant.IS_DELETE);
            question.setUpdateBy(Common.getUsernameLogin());
            question.setUpdateAt(Common.getSystemDate());
            listQuestion.add(question);
        }
        chapter.setListQuestion(listQuestion);
        chapterResponsitory.save(chapter);
    }
}
