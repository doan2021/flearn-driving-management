package com.doanfpt.management.application.services;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.doanfpt.management.application.common.Common;
import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.dto.ExamForm;
import com.doanfpt.management.application.dto.FormSearchExam;
import com.doanfpt.management.application.entities.Exam;
import com.doanfpt.management.application.responsitories.ExamRepository;
import com.doanfpt.management.application.specification.ExamSpecification;

@Service
public class ExamService {
	@Autowired
	private ExamRepository examRepository;

	public List<Exam> listAll() {
		return examRepository.findByIsDeleteAndIsTrial(false, false);
	}

	public void saveExam(ExamForm examForm) {
		Exam exam = new Exam();
		if (examForm.getIsUpdate()) {
			exam = examRepository.getOne(examForm.getExamId());
		}
		exam.setName(examForm.getName());
		exam.setDescription(examForm.getDescription());
		exam.setDateRegisExamStart(Common.stringToDate(examForm.getDateRegisExamStart()));
		exam.setDateRegisExamEnd(Common.stringToDate(examForm.getDateRegisExamEnd()));
		Date dateExam = Common.addDays(Common.stringToDate(examForm.getDateRegisExamEnd()), 15);
		exam.setDateExam(dateExam);
		if (!examForm.getIsUpdate()) {
			exam.setCreateBy(Common.getUsernameLogin());
			exam.setCreateAt(Common.getSystemDate());
		}
		exam.setUpdateBy(Common.getUsernameLogin());
		exam.setUpdateAt(Common.getSystemDate());
		examRepository.save(exam);
	}

	public ExamForm getObjectUpdate(Long examId) {
		ExamForm examForm = new ExamForm();
		Exam exam = examRepository.getOne(examId);
		examForm.setExamId(exam.getExamId());
		examForm.setName(exam.getName());
		examForm.setDescription(exam.getDescription());
		examForm.setDateRegisExamStart(DateFormatUtils.format(exam.getDateRegisExamStart(), Constant.FORMAT_DATE));
		examForm.setDateRegisExamEnd(DateFormatUtils.format(exam.getDateRegisExamEnd(), Constant.FORMAT_DATE));
		examForm.setDateExam(DateFormatUtils.format(exam.getDateExam(), Constant.FORMAT_DATE));
		examForm.setUpdateAt(DateFormatUtils.format(exam.getUpdateAt(), Constant.FORMAT_DATE));
		examForm.setUpdateBy(exam.getUpdateBy());
		return examForm;

	}

	public Page<Exam> getAllExam(Integer pageNumber) {
		if (pageNumber == null) {
			pageNumber = 0;
		}
		Specification<Exam> conditions = Specification.where(ExamSpecification.isDelete(false));
		PageRequest pageable = PageRequest.of(pageNumber, Constant.RECORD_PER_PAGE);
		Page<Exam> listExam = examRepository.findAll(conditions, pageable);
		return listExam;
	}

	public Page<Exam> searchExam(FormSearchExam formSearchExam) {
		if (formSearchExam.getPageNumber() == null) {
			formSearchExam.setPageNumber(0);
		}
		// Init condition with is_delete
		Specification<Exam> conditions = Specification.where(ExamSpecification.isDelete(false));
		if (formSearchExam != null) {
			if (StringUtils.isEmptyOrWhitespace(formSearchExam.getName())) {
				conditions = conditions.and(ExamSpecification.hasName(formSearchExam.getName()));
			}
			if (StringUtils.isEmptyOrWhitespace(formSearchExam.getDescription())) {
				conditions = conditions.and(ExamSpecification.hasDescription(formSearchExam.getDescription()));
			}
		}

		PageRequest pageable = PageRequest.of(formSearchExam.getPageNumber(), Constant.RECORD_PER_PAGE);
		Page<Exam> listExam = examRepository.findAll(conditions, pageable);
		return listExam;
	}
}
