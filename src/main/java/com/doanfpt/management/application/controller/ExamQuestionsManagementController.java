package com.doanfpt.management.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.respone.ResponeData;
import com.doanfpt.management.application.services.ChapterServices;
import com.doanfpt.management.application.services.DrivingLicenseServices;
import com.doanfpt.management.application.services.ExamServices;
import com.doanfpt.management.application.services.QuestionServices;

@Controller
@RequestMapping("/management")
public class ExamQuestionsManagementController {

    @Autowired
    ExamServices examServices;

    @Autowired
    ChapterServices chapterServices;
    
    @Autowired
    DrivingLicenseServices drivingLicenseServices;

    @GetMapping(value = { "/exam-questions" })
    public String visitExamQuestionManagementPage(Integer pageNumber, Model model) {
        return "exam-questions-management";
    }

    @GetMapping(value = { "/create-exam-questions" })
    public String visitCreateExamQuestionPage(Model model) {
        return "create-exam-questions";
    }
    
    @GetMapping(value = { "/init-create-exam-questions" })
    public @ResponseBody ResponeData initExamQuestion() {
        ResponeData responeData = new ResponeData();
        responeData.putResult("listDrivingLicense", drivingLicenseServices.findAll());
        responeData.putResult("listChapter", chapterServices.findAll());
        return responeData;
    }
}
