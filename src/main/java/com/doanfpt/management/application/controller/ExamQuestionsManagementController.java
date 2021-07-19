package com.doanfpt.management.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.dto.ExamQuestionsForm;
import com.doanfpt.management.application.services.ExamServices;
import com.doanfpt.management.application.services.QuestionServices;

@Controller
@RequestMapping("/management")
public class ExamQuestionsManagementController {

	@Autowired
	ExamServices examServices;
	
	@Autowired
	QuestionServices questionServices;

	@GetMapping(value = { "/exam-questions-management" })
	public String examQuestionManagement(Integer pageNumber, Model model) {
		return "exam-questions-management";
	}
	
	@GetMapping(value = { "/create-exam-questions" })
	public String createExamQuestionManagement(Integer pageNumber, Model model) {
		ExamQuestionsForm examQuestionsForm = new ExamQuestionsForm();
		model.addAttribute("examQuestionsForm", examQuestionsForm);
		model.addAttribute(Constant.PAGE_CONTENT_NAME, questionServices.getAllQuestions(pageNumber));
		return "create-exam-questions";
	}

}
