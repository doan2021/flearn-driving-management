package com.doanfpt.management.application.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.doanfpt.management.application.common.Common;
import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.dto.DrivingLicenseForm;
import com.doanfpt.management.application.dto.FormSearchDrivingLicense;
import com.doanfpt.management.application.entities.Chapter;
import com.doanfpt.management.application.entities.DrivingLicense;
import com.doanfpt.management.application.entities.ExamStructure;
import com.doanfpt.management.application.model.NumberOfChapter;
import com.doanfpt.management.application.respositories.ChapterRespository;
import com.doanfpt.management.application.respositories.DrivingLicenseRespository;
import com.doanfpt.management.application.specification.DrivingLicenseSpecification;

@Service
public class DrivingLicenseServices {

	@Autowired
	private DrivingLicenseRespository drivingLicenseRespository;

	@Autowired
	private ChapterRespository chapterRespository;

	public List<DrivingLicense> findAllDrivingLicense() {
        return drivingLicenseRespository.findByIsDelete(Constant.IS_NOT_DELETE);
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
				Chapter chapter = chapterRespository.findByChapterIdAndIsDelete(numberOfChapter.getChapterId(),
						Constant.IS_NOT_DELETE);
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

	public Object getObjectUpdate(Long drivingLicenseId) {
		DrivingLicenseForm drivingLicenseForm = new DrivingLicenseForm();
		DrivingLicense drivingLicense = new DrivingLicense();
		drivingLicense.setName(drivingLicenseForm.getName());
		drivingLicense.setPrice(NumberUtils.toDouble(drivingLicenseForm.getPrice()));
		drivingLicense.setNumberQuestion(NumberUtils.toInt(drivingLicenseForm.getNumberQuestion()));
		drivingLicense.setNumberQuestionParalysis(NumberUtils.toInt(drivingLicenseForm.getNumberQuestionParalysis()));
		drivingLicense.setNumberQuestionCorect(NumberUtils.toInt(drivingLicenseForm.getNumberQuestionCorect()));
		drivingLicense.setExamMinutes(NumberUtils.toInt(drivingLicenseForm.getExamMinutes()));
		drivingLicense.setDescription(drivingLicenseForm.getDescription());
		drivingLicense.setNumberYearExpires(NumberUtils.toInt(drivingLicenseForm.getNumberYearExpires()));
//		List<ExamStructure> listExamStructures = new ArrayList<>();
//		for (NumberOfChapter numberOfChapter : drivingLicenseForm.getListNumberOfChapter()) {
//			if (numberOfChapter.getChapterId() != null) {
//				Chapter chapter = chapterRespository.findByChapterIdAndIsDelete(numberOfChapter.getChapterId(),
//						Constant.IS_NOT_DELETE);
//				ExamStructure examStructure = new ExamStructure();
//				examStructure.setChapter(chapter);
//				examStructure.setDrivingLicense(drivingLicense);
//				listExamStructures.add(examStructure);
//			}
//		}
//		drivingLicense.setListExamStructure(listExamStructures);
		drivingLicense.setCreateBy(Common.getUsernameLogin());
		drivingLicense.setCreateAt(Common.getSystemDate());
		drivingLicense.setUpdateBy(Common.getUsernameLogin());
		drivingLicense.setUpdateAt(Common.getSystemDate());
		return drivingLicenseForm;

	}

	public void updateDrivingLicense(DrivingLicenseForm drivingLicenseForm) {
		DrivingLicense drivingLicense = drivingLicenseRespository
				.findByDrivingLicenseIdAndIsDelete(drivingLicenseForm.getDrivingLicenseId(), Constant.IS_NOT_DELETE);
		drivingLicense.setName(drivingLicenseForm.getName());
		drivingLicense.setPrice(NumberUtils.toDouble(drivingLicenseForm.getPrice()));
		drivingLicense.setNumberQuestion(NumberUtils.toInt(drivingLicenseForm.getNumberQuestion()));
		drivingLicense.setNumberQuestionParalysis(NumberUtils.toInt(drivingLicenseForm.getNumberQuestionParalysis()));
		drivingLicense.setNumberQuestionCorect(NumberUtils.toInt(drivingLicenseForm.getNumberQuestionCorect()));
		drivingLicense.setExamMinutes(NumberUtils.toInt(drivingLicenseForm.getExamMinutes()));
		drivingLicense.setDescription(drivingLicenseForm.getDescription());
		drivingLicense.setNumberYearExpires(NumberUtils.toInt(drivingLicenseForm.getNumberYearExpires()));
//		List<ExamStructure> listExamStructures = new ArrayList<>();
//		for (NumberOfChapter numberOfChapter : drivingLicenseForm.getListNumberOfChapter()) {
//			if (numberOfChapter.getChapterId() != null) {
//				Chapter chapter = chapterRespository.findByChapterIdAndIsDelete(numberOfChapter.getChapterId(), Constant.IS_NOT_DELETE);
//				ExamStructure examStructure = new ExamStructure();
//				examStructure.setChapter(chapter);
//				examStructure.setDrivingLicense(drivingLicense);
//				listExamStructures.add(examStructure);
//			}
//		}
//		drivingLicense.setListExamStructure(listExamStructures);
		drivingLicense.setCreateBy(Common.getUsernameLogin());
		drivingLicense.setCreateAt(Common.getSystemDate());
		drivingLicense.setUpdateBy(Common.getUsernameLogin());
		drivingLicense.setUpdateAt(Common.getSystemDate());
		drivingLicenseRespository.save(drivingLicense);
	}
	
    public Page<DrivingLicense> searchDrivingLicense(FormSearchDrivingLicense formSearchDrivingLicense) {
        if (formSearchDrivingLicense.getPageNumber() == null) {
        	formSearchDrivingLicense.setPageNumber(0);
        }
        // Init condition with is_delete
        Specification<DrivingLicense> conditions = Specification.where(DrivingLicenseSpecification.isDelete(false));
        if (formSearchDrivingLicense != null) {
            if (StringUtils.isNotBlank(formSearchDrivingLicense.getName())) {
                conditions = conditions.and(DrivingLicenseSpecification.hasName(formSearchDrivingLicense.getName()));
            }
            if (StringUtils.isNotBlank(formSearchDrivingLicense.getPrice())) {
                conditions = conditions.and(DrivingLicenseSpecification.hasPrice(formSearchDrivingLicense.getPrice()));
            }
            if (StringUtils.isNotBlank(formSearchDrivingLicense.getNumberQuestion())) {
                conditions = conditions.and(DrivingLicenseSpecification.hasNumberQuestion(formSearchDrivingLicense.getNumberQuestion()));
            }
            if (StringUtils.isNotBlank(formSearchDrivingLicense.getNumberQuestionParalysis())) {
                conditions = conditions.and(DrivingLicenseSpecification.hasNumberQuestionParalysis(formSearchDrivingLicense.getNumberQuestionParalysis()));
            }
            if (StringUtils.isNotBlank(formSearchDrivingLicense.getExamMinutes())) {
                conditions = conditions.and(DrivingLicenseSpecification.hasExamMinutes(formSearchDrivingLicense.getExamMinutes()));
            }
            if (StringUtils.isNotBlank(formSearchDrivingLicense.getNumberYearExpires())) {
                conditions = conditions.and(DrivingLicenseSpecification.hasNumberYearExpires(formSearchDrivingLicense.getNumberYearExpires()));
            }
        }
        Pageable pageable = PageRequest.of(formSearchDrivingLicense.getPageNumber(), Constant.RECORD_PER_PAGE);
//        Page<DrivingLicense> listDrivingLicense = drivingLicenseRespository.findAll(conditions, pageable);
        Page<DrivingLicense> listDrivingLicense = drivingLicenseRespository.findAll(conditions, pageable);
        return listDrivingLicense;
    }
}
