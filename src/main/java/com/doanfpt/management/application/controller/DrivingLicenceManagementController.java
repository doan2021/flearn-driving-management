package com.doanfpt.management.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.dto.DrivingLicenseForm;
import com.doanfpt.management.application.dto.FormSearchDrivingLicense;
import com.doanfpt.management.application.dto.FormSearchExamQuestions;
import com.doanfpt.management.application.services.ChapterServices;

@Controller
@RequestMapping("/management")
public class DrivingLicenceManagementController {
    
    @Autowired
    ChapterServices chapterServices;
    
    @GetMapping(value = { "/driving-license" })
    public String visitDrivingLicensePage(FormSearchDrivingLicense formSearchDrivingLicense, Model model) {
        model.addAttribute(Constant.PAGE_CONTENT_NAME,
                drivingLicenseServices.searchExamQuestions(FormSearchDrivingLicense));
        model.addAttribute("formSearchExamQuestions", new FormSearchExamQuestions());
        return "driving-license-management";
    }
    
    @PostMapping(value = { "/search-exam-questions" })
    public String searchExamQuestions(FormSearchExamQuestions formSearchExamQuestions, Model model) {
        model.addAttribute(Constant.PAGE_CONTENT_NAME,
                examQuestionsServices.searchExamQuestions(formSearchExamQuestions));
        model.addAttribute("formSearchExamQuestions", formSearchExamQuestions);
        return "exam-questions-management";
    }

    @GetMapping(value = { "/driving-license" })
    public String visitDrivingLicenseManagementPage(Model model) {
        return "driving-license-management";
    }
    
    @GetMapping(value = { "/create-driving-license" })
    public String visitCreateDrivingLicenseManagementPage(Model model) {
        DrivingLicenseForm drivingLicenseForm = new DrivingLicenseForm();
        model.addAttribute("drivingLicenseForm", drivingLicenseForm);
        model.addAttribute("listChapter", chapterServices.findAllChapter());
        return "create-driving-license";
    }
}
