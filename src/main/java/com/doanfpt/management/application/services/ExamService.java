package com.doanfpt.management.application.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doanfpt.management.application.common.Common;
import com.doanfpt.management.application.dto.ExamForm;
import com.doanfpt.management.application.entities.Exam;
import com.doanfpt.management.application.responsitories.ExamRepository;

@Service
public class ExamService {
	@Autowired
	private ExamRepository examRepository;
	
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
		examRepository.save(exam);
	}
	
	public Exam get(Long examId) {
		return examRepository.findById(examId).get();
	}
	
//	public void deleteExam(Long id) {
//		examRepository.deleteById(id);
//	}
	
}
