package com.flearndriving.management.application.services;

import com.flearndriving.management.application.common.Common;
import com.flearndriving.management.application.common.Constant;
import com.flearndriving.management.application.common.MimeTypes;
import com.flearndriving.management.application.dto.ChapterForm;
import com.flearndriving.management.application.dto.FormSearchChapter;
import com.flearndriving.management.application.entities.Chapter;
import com.flearndriving.management.application.entities.Chapter_;
import com.flearndriving.management.application.entities.Document;
import com.flearndriving.management.application.exception.BusinessException;
import com.flearndriving.management.application.respositories.ChapterRepository;
import com.flearndriving.management.application.respositories.DocumentRepository;
import com.flearndriving.management.application.respositories.QuestionsRepository;
import com.flearndriving.management.application.specification.ChapterSpecification;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChapterServices {

    @Autowired
    ChapterRepository chapterResponsitory;

    @Autowired
    QuestionsRepository questionsRepository;

    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    private final CommonServices commonServices;

    public Chapter getChapterDetail(Long chapterId) {
        Chapter chapter = chapterResponsitory.findByChapterId(chapterId);
        if (chapter == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Chương không tồn tại!");
        }
        return chapter;
    }

    public List<Chapter> findAllChapter() {
        return chapterResponsitory.findAll(
                Sort.by(Sort.Direction.ASC, Chapter_.NAME).and(Sort.by(Sort.Direction.ASC, Chapter_.UPDATE_AT)));
    }

    @Transactional
    public void createChapter(ChapterForm chapterForm) {
        if (chapterForm == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Dữ liệu truyền vào không đúng!");
        }
        Chapter chapter = new Chapter();
        chapter.setName(chapterForm.getName());
        chapter.setContent(chapterForm.getContent());
        chapter.setDescription(chapterForm.getDescription());
        chapter.setCreateBy(commonServices.getUsernameLogin());
        chapter.setCreateAt(Common.getSystemDate());
        chapter.setUpdateBy(commonServices.getUsernameLogin());
        chapter.setUpdateAt(Common.getSystemDate());

        // Handle document
        List<Document> listDocuments = new ArrayList<>();
        if (chapterForm.getImages() != null && !chapterForm.getImages()[0].isEmpty()) {
            // Get file to client
            for (MultipartFile multipartFile : chapterForm.getImages()) {
                Document document = new Document();
                document.setFileName(Common.generateFileName(multipartFile, Constant.DOCUMENT_ORTHER_LABEL));
                document.setOriginFileName(multipartFile.getOriginalFilename());
                document.setExtension(MimeTypes.lookupExt(multipartFile.getContentType()));
                document.setContentType(multipartFile.getContentType());
                document.setSize(multipartFile.getSize());
                document.setType(Constant.TYPE_DOCUMENT_ORTHER);
                document.setDescription("Ảnh mô tả chương");
                document.setCreateAt(Common.getSystemDate());
                document.setCreateBy(commonServices.getUsernameLogin());
                // TODO Upload Image
                //document.setPath(amazonS3ClientService.uploadFileToS3Bucket(multipartFile, document.getFileName()));
                listDocuments.add(document);
            }
            chapter.setListImages(listDocuments);
        }
        chapterResponsitory.save(chapter);
    }

    @Transactional
    public void updateChapter(ChapterForm chapterForm) {
        Chapter chapter = chapterResponsitory.findByChapterId(chapterForm.getChapterId());
        chapter.setName(chapterForm.getName());
        chapter.setName(chapterForm.getName());
        chapter.setDescription(chapterForm.getDescription());
        chapter.setUpdateBy(commonServices.getUsernameLogin());
        chapter.setUpdateAt(Common.getSystemDate());
        // Handle document
        if (chapterForm.getImages() != null && !chapterForm.getImages()[0].isEmpty()) {
            for (Document doc : chapter.getListImages()) {
                // TODO Upload Image
                //amazonS3ClientService.deleteFileFromS3Bucket(doc.getFileName());
            }
            documentRepository.deleteAll(chapter.getListImages());
            List<Document> listDocuments = new ArrayList<>();
            // Delete list old image
            for (MultipartFile multipartFile : chapterForm.getImages()) {
                Document document = new Document();
                document.setFileName(Common.generateFileName(multipartFile, Constant.DOCUMENT_ORTHER_LABEL));
                document.setOriginFileName(multipartFile.getOriginalFilename());
                document.setExtension(MimeTypes.lookupExt(multipartFile.getContentType()));
                document.setContentType(multipartFile.getContentType());
                document.setSize(multipartFile.getSize());
                document.setType(Constant.TYPE_DOCUMENT_ORTHER);
                document.setDescription("Ảnh mô tả chương");
                document.setCreateAt(Common.getSystemDate());
                document.setCreateBy(commonServices.getUsernameLogin());
                // TODO Upload Image
                //document.setPath(amazonS3ClientService.uploadFileToS3Bucket(multipartFile, document.getFileName()));
                listDocuments.add(document);
            }
            chapter.setListImages(listDocuments);
        }
        chapterResponsitory.save(chapter);
    }

    public Page<Chapter> searchChapter(FormSearchChapter formSearchChapter) {
        if (formSearchChapter.getPageNumber() == null) {
            formSearchChapter.setPageNumber(0);
        }
        Specification<Chapter> conditions = Specification.where(ChapterSpecification.isDelete(false));
        if (formSearchChapter != null) {
            if (StringUtils.isNotBlank(formSearchChapter.getIndex())) {
                conditions = conditions.and(ChapterSpecification.hasIndex(formSearchChapter.getName()));
            }
            if (StringUtils.isNotBlank(formSearchChapter.getName())) {
                conditions = conditions.and(ChapterSpecification.likeName(formSearchChapter.getName()));
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
        Chapter chapter = chapterResponsitory.findByChapterId(chapterId);
        chapterForm.setChapterId(chapter.getId());
        chapterForm.setName(chapter.getName());
        chapterForm.setName(chapter.getName());
        chapterForm.setDescription(chapter.getDescription());
        chapterForm.setUpdateAt(DateFormatUtils.format(chapter.getUpdateAt(), Constant.FORMAT_DATE_TIME));
        chapterForm.setUpdateBy(commonServices.getUsernameLogin());
        return chapterForm;
    }

    public Integer countChapter() {
        return chapterResponsitory.countChapter();
    }

    @Transactional
    public void deleteChapter(Long chapterId) {
        Chapter chapter = chapterResponsitory.findByChapterId(chapterId);
        if (chapter == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Chương không tồn tại!");
        }
        chapter.setIsDelete(true);
        chapter.setUpdateAt(Common.getSystemDate());
        chapter.setUpdateBy(commonServices.getUsernameLogin());
        chapterResponsitory.save(chapter);
    }
}
