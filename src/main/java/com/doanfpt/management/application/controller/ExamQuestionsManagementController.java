package com.doanfpt.management.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doanfpt.management.application.dto.ExamQuestionsForm;
import com.doanfpt.management.application.services.DrivingLicenseServices;
import com.doanfpt.management.application.services.QuestionServices;

@Controller
@RequestMapping("/management")
public class ExamQuestionsManagementController {

    @Autowired
    QuestionServices questionServices;

    @Autowired
    DrivingLicenseServices drivingLicenseServices;


    @GetMapping(value = { "/create-exam-questions" })
    public String visitCreateExamQuestionPage(Long drivingLicenseId, Model model) {
        ExamQuestionsForm examQuestionsForm = new ExamQuestionsForm();
        model.addAttribute("examQuestionsForm", examQuestionsForm);
        model.addAttribute("drivingLicense", drivingLicenseServices.findById(drivingLicenseId)) ;
        return "create-exam-questions";
    }

    @PostMapping(value = { "/create-exam-questions" })
    public String createExamQuestion(ExamQuestionsForm examQuestionsForm, Model model) {
        model.addAttribute("drivingLicense", drivingLicenseServices.findById(examQuestionsForm.getDrivingLicenseId())) ;
        return "create-exam-questions";
    }
}
