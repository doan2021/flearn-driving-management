package com.doanfpt.management.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.dto.ExamForm;
import com.doanfpt.management.application.dto.FormSearchExam;
import com.doanfpt.management.application.services.ExamServices;

@Controller
@RequestMapping("/management")
public class ExamManagementController {

	@Autowired
	private ExamServices examService;

	@GetMapping(value = { "/exam" })
	public String visitExamPage(FormSearchExam formSearchExam, Model model) {
		formSearchExam = new FormSearchExam();
		model.addAttribute(Constant.PAGE_CONTENT_NAME, examService.searchExam(formSearchExam));
		model.addAttribute("formSearchExam", formSearchExam);
		return "exam-management";
	}

	@GetMapping(value = { "/create-exam" })
	public String createExamPage(Model model) {
		ExamForm examForm = new ExamForm();
		model.addAttribute("examForm", examForm);
		return "create-exam"; 
	}

	@PostMapping(value = { "/save-exam" })
	public String saveExam(@ModelAttribute("examForm") ExamForm examForm) {
		examService.saveExam(examForm);
		if (examForm.getIsUpdate()) {
			return "edit-exam";
		}
		return "create-exam";
	}

	@RequestMapping("/edit-exam")
	public String showEditExamForm(Long examId, Model model) {
		model.addAttribute("examForm", examService.getObjectUpdate(examId));
		return "edit-exam";
	}

	@PostMapping(value = { "/search-exam" })
	public String searchExam(FormSearchExam formSearchExam, Model model) {
		model.addAttribute(Constant.PAGE_CONTENT_NAME, examService.searchExam(formSearchExam));
		model.addAttribute("formSearchExam", formSearchExam);
		return "exam-management";
	}

	@GetMapping(value = { "/delete-exam" })
	public String deleteExam(Long examId, Model model) {
		examService.deleteExam(examId);
		model.addAttribute("messageSuccess", "Đã xóa 1 record!");
		FormSearchExam formSearchExam = new FormSearchExam();
		model.addAttribute(Constant.PAGE_CONTENT_NAME, examService.searchExam(formSearchExam));
		model.addAttribute("formSearchExam", formSearchExam);
		return "exam-management";
	}

}
