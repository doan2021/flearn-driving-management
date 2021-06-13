package com.doanfpt.management.application.services;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doanfpt.management.application.common.Common;
import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.dto.ExamForm;
import com.doanfpt.management.application.entities.Exam;
import com.doanfpt.management.application.responsitories.ExamRepository;

@Service
public class ExamService {
	@Autowired
	private ExamRepository examRepository;
	
	@Autowired
	AccountServices accountServices;
	
	public List<Exam> listAll() {
		return examRepository.findAll();
	}
	
	public void saveExam(ExamForm examForm) {
		Exam exam = new Exam();
		exam.setName(examForm.getName());
		exam.setDescription(examForm.getDescription());
		exam.setDateRegisExamStart(Common.stringToDate(examForm.getDateRegisExamStart()));
		exam.setDateRegisExamEnd(Common.stringToDate(examForm.getDateRegisExamEnd()));
		Date dateExam = Common.addDays(Common.stringToDate(examForm.getDateRegisExamEnd()), 15);
		exam.setDateExam(dateExam);
		exam.setCreateBy(Common.getUsernameLogin());
		exam.setCreateAt(Common.getSystemDate());
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
	
//	public void deleteExam(Long id) {
//		examRepository.deleteById(id);
//	}
	
}
