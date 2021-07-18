package com.doanfpt.management.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.doanfpt.management.application.dto.ExamForm;
import com.doanfpt.management.application.services.ExamServices;

@Controller
@RequestMapping("/management")
public class ExamQuestionsManagementController {

	@Autowired
	ExamServices examServices;

	@GetMapping(value = { "/exam-questions-management" })
	public String examQuestionManagement(Model model) {
		return "exam-questions-management";
	}
	
	@GetMapping(value = { "/create-exam-questions" })
	public String createExamQuestionManagement(Model model) {
		return "create-exam-questions";
	}

}
