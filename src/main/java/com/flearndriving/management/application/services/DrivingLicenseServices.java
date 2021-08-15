package com.flearndriving.management.application.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.flearndriving.management.application.common.Common;
import com.flearndriving.management.application.common.Constant;
import com.flearndriving.management.application.dto.DrivingLicenseForm;
import com.flearndriving.management.application.dto.FormSearchDrivingLicense;
import com.flearndriving.management.application.dto.NumberOfChapter;
import com.flearndriving.management.application.entities.Chapter;
import com.flearndriving.management.application.entities.DrivingLicense;
import com.flearndriving.management.application.entities.ExamStructure;
import com.flearndriving.management.application.exception.BusinessException;
import com.flearndriving.management.application.respositories.ChapterRespository;
import com.flearndriving.management.application.respositories.DrivingLicenseRespository;
import com.flearndriving.management.application.specification.DrivingLicenseSpecification;

@Service
public class DrivingLicenseServices {

    @Autowired
    private DrivingLicenseRespository drivingLicenseRespository;

    @Autowired
    private ChapterRespository chapterRespository;

    public List<DrivingLicense> findAllDrivingLicense() {
        return drivingLicenseRespository.findAll();
    }

    public DrivingLicense findById(Long drivingLicenseId) {
        return drivingLicenseRespository.getOne(drivingLicenseId);
    }

    @Transactional
    public void createDrivingLicense(DrivingLicenseForm drivingLicenseForm) {
        DrivingLicense drivingLicense = new DrivingLicense();
        drivingLicense.setName(drivingLicenseForm.getName());
        drivingLicense.setPrice(NumberUtils.toDouble(drivingLicenseForm.getPrice()));
        drivingLicense.setNumberQuestion(NumberUtils.toInt(drivingLicenseForm.getNumberQuestion()));
        drivingLicense.setNumberQuestionParalysis(NumberUtils.toInt(drivingLicenseForm.getNumberQuestionParalysis()));
        drivingLicense.setNumberQuestionCorect(NumberUtils.toInt(drivingLicenseForm.getNumberQuestionCorect()));
        drivingLicense.setExamMinutes(NumberUtils.toInt(drivingLicenseForm.getExamMinutes()));
        drivingLicense.setDescription(drivingLicenseForm.getDescription());
        drivingLicense.setNumberYearExpires(NumberUtils.toInt(drivingLicenseForm.getNumberYearExpires()));

        // Create structure
        List<ExamStructure> listExamStructures = new ArrayList<>();
        for (NumberOfChapter numberOfChapter : drivingLicenseForm.getListNumberOfChapter()) {
            if (numberOfChapter.getChapterId() != null
                    && NumberUtils.toInt(numberOfChapter.getNumberQuestionInChapter()) != 0) {
                Chapter chapter = chapterRespository.findByChapterId(numberOfChapter.getChapterId());
                ExamStructure examStructure = new ExamStructure();
                examStructure.setChapter(chapter);
                examStructure.setNumberQuestion(NumberUtils.toInt(numberOfChapter.getNumberQuestionInChapter()));
                examStructure.setDrivingLicense(drivingLicense);
                listExamStructures.add(examStructure);
            }
        }
        drivingLicense.setListExamStructure(listExamStructures);
        drivingLicense.setCreateBy(Common.getUsernameLogin());
        drivingLicense.setCreateAt(Common.getSystemDate());
        drivingLicense.setUpdateBy(Common.getUsernameLogin());
        drivingLicense.setUpdateAt(Common.getSystemDate());
        drivingLicenseRespository.save(drivingLicense);
    }

    @Transactional
    public void deleteDrivingLicense(Long drivingLicenseId){
        DrivingLicense drivingLicense = drivingLicenseRespository.findByDrivingLicenseId(drivingLicenseId);
        if (drivingLicense == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Hạng bằng không tồn tại!");
        }
        drivingLicenseRespository.delete(drivingLicense);
    }

    public DrivingLicenseForm getObjectUpdate(Long drivingLicenseId) {
        DrivingLicenseForm drivingLicenseForm = new DrivingLicenseForm();
        DrivingLicense drivingLicense = drivingLicenseRespository.findByDrivingLicenseId(drivingLicenseId);
        if (drivingLicense == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Hạng bằng không tồn tại!");
        }
        drivingLicenseForm.setName(drivingLicense.getName());
        drivingLicenseForm.setPrice(String.valueOf(drivingLicense.getPrice()));
        drivingLicenseForm.setNumberQuestion(String.valueOf(drivingLicense.getNumberQuestion()));
        drivingLicenseForm.setNumberQuestionParalysis(String.valueOf(drivingLicense.getNumberQuestionParalysis()));
        drivingLicenseForm.setNumberQuestionCorect(String.valueOf(drivingLicense.getNumberQuestionCorect()));
        drivingLicenseForm.setExamMinutes(String.valueOf(drivingLicense.getExamMinutes()));
        drivingLicenseForm.setDescription(drivingLicense.getDescription());
        drivingLicenseForm.setNumberYearExpires(String.valueOf(drivingLicense.getNumberYearExpires()));
        List<NumberOfChapter> listNumberOfChapter = new ArrayList<>();
        for (ExamStructure examStructure : drivingLicense.getListExamStructure()) {
            NumberOfChapter numberOfChapter = new NumberOfChapter();
            numberOfChapter.setChapterId(examStructure.getChapter().getChapterId());
            numberOfChapter.setNumberQuestionInChapter(String.valueOf(examStructure.getNumberQuestion()));
            listNumberOfChapter.add(numberOfChapter);
        }
        drivingLicenseForm.setListNumberOfChapter(listNumberOfChapter);
        return drivingLicenseForm;

    }

    public void updateDrivingLicense(DrivingLicenseForm drivingLicenseForm) {
    }

    public Page<DrivingLicense> searchDrivingLicense(FormSearchDrivingLicense formSearchDrivingLicense) {
        if (formSearchDrivingLicense.getPageNumber() == null) {
            formSearchDrivingLicense.setPageNumber(0);
        }
        Specification<DrivingLicense> conditions = Specification.where(null);
        if (formSearchDrivingLicense != null) {
            if (StringUtils.isNotBlank(formSearchDrivingLicense.getName())) {
                conditions = conditions.and(DrivingLicenseSpecification.hasName(formSearchDrivingLicense.getName()));
            }
            if (StringUtils.isNotBlank(formSearchDrivingLicense.getPrice())) {
                conditions = conditions.and(DrivingLicenseSpecification.hasPrice(formSearchDrivingLicense.getPrice()));
            }
            if (StringUtils.isNotBlank(formSearchDrivingLicense.getNumberQuestion())) {
                conditions = conditions.and(
                        DrivingLicenseSpecification.hasNumberQuestion(formSearchDrivingLicense.getNumberQuestion()));
            }
            if (StringUtils.isNotBlank(formSearchDrivingLicense.getNumberQuestionParalysis())) {
                conditions = conditions.and(DrivingLicenseSpecification
                        .hasNumberQuestionParalysis(formSearchDrivingLicense.getNumberQuestionParalysis()));
            }
            if (StringUtils.isNotBlank(formSearchDrivingLicense.getExamMinutes())) {
                conditions = conditions
                        .and(DrivingLicenseSpecification.hasExamMinutes(formSearchDrivingLicense.getExamMinutes()));
            }
            if (StringUtils.isNotBlank(formSearchDrivingLicense.getNumberYearExpires())) {
                conditions = conditions.and(DrivingLicenseSpecification
                        .hasNumberYearExpires(formSearchDrivingLicense.getNumberYearExpires()));
            }
        }
        Pageable pageable = PageRequest.of(formSearchDrivingLicense.getPageNumber(), Constant.RECORD_PER_PAGE);
        Page<DrivingLicense> listDrivingLicense = drivingLicenseRespository.findAll(conditions, pageable);
        return listDrivingLicense;
    }
}
