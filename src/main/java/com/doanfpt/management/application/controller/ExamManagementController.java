package com.doanfpt.management.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.doanfpt.management.application.dto.ExamForm;
import com.doanfpt.management.application.entities.Exam;
import com.doanfpt.management.application.services.ExamService;

@Controller
@RequestMapping("/management")
public class ExamManagementController {
	
	@Autowired
    private ExamService examService;
	
    @GetMapping(value = {"/exam"})
    public String visitExamPage(Model model) {
    	List<Exam> listExams = examService.listAll();
    	model.addAttribute("listExams", listExams);
        return "exam-management";
    }
    
    @GetMapping(value = {"/create-exam"})
    public String createExamPage(Model model) {
    	ExamForm examForm = new ExamForm();
    	model.addAttribute("examForm", examForm);
        return "create-exam";
    }
    
    @RequestMapping(value = "/create-exam", method = RequestMethod.POST)
    public String saveExam(@ModelAttribute("examForm") ExamForm examForm) {
    	examService.saveExam(examForm);
    	return "create-exam";
    }
    
    @RequestMapping("/edit_exam/{examId}")
    public ModelAndView showEditExamForm(@PathVariable(name = "examId") Long examId) {
    	ModelAndView mav = new ModelAndView("edit_exam");
    	
    	Exam exam = examService.get(examId);
    	mav.addObject("exam", exam);
    	
    	return mav;
    	
    }
}
