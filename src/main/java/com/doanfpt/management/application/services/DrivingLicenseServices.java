package com.doanfpt.management.application.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doanfpt.management.application.common.Common;
import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.dto.DrivingLicenseForm;
import com.doanfpt.management.application.entities.Chapter;
import com.doanfpt.management.application.entities.DrivingLicense;
import com.doanfpt.management.application.entities.ExamStructure;
import com.doanfpt.management.application.model.NumberOfChapter;
import com.doanfpt.management.application.respositories.ChapterRespository;
import com.doanfpt.management.application.respositories.DrivingLicenseRespository;

@Service
public class DrivingLicenseServices {

    @Autowired
    private DrivingLicenseRespository drivingLicenseRespository;
    
    @Autowired
    private ChapterRespository chapterRespository;

    public List<DrivingLicense> findAll() {
        return drivingLicenseRespository.findAll();
    }

    public DrivingLicense findById(Long drivingLicenseId) {
        return drivingLicenseRespository.getOne(drivingLicenseId);
    }
    
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
            if (numberOfChapter.getChapterId() != null) {
                Chapter chapter = chapterRespository.findByChapterIdAndIsDelete(numberOfChapter.getChapterId(), Constant.IS_NOT_DELETE);
                ExamStructure examStructure = new ExamStructure();
                examStructure.setChapter(chapter);
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
}
