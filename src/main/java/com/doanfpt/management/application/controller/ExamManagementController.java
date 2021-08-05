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
import com.doanfpt.management.application.dto.ExamUpdateForm;
import com.doanfpt.management.application.dto.FormSearchExam;

import com.doanfpt.management.application.services.DrivingLicenseServices;
import com.doanfpt.management.application.services.ExamServices;

@Controller
@RequestMapping("/management")
public class ExamManagementController {

    @Autowired
    private ExamServices examService;
    
    @Autowired
    private DrivingLicenseServices drivingLicenseServices;

    @GetMapping(value = { "/exam" })
    public String visitExamPage(FormSearchExam formSearchExam, Model model) {
        formSearchExam = new FormSearchExam();
        model.addAttribute(Constant.PAGE_CONTENT_NAME, examService.searchExam(formSearchExam));
        model.addAttribute("formSearchExam", formSearchExam);
        return "exam-management";
    }

    @GetMapping(value = { "/create-exam" })
    public String visitCreateExamPage(Model model) {
        ExamForm examForm = new ExamForm();
        model.addAttribute("examForm", examForm);
        model.addAttribute("listDrivingLicense", drivingLicenseServices.findAllDrivingLicense());
        return "create-exam";
    }

    @PostMapping(value = { "/create-exam" })
    public String createExam(@ModelAttribute("examForm") ExamForm examForm, Model model) {
        examService.createExam(examForm);
        model.addAttribute("listDrivingLicense", drivingLicenseServices.findAllDrivingLicense());
        return "create-exam";
    }
    
    @GetMapping(value = {"/detail-exam"})
    public String visitDetailExamPage(Long examId, Model model) {
        model.addAttribute("exam", examService.getOne(examId));
        return "detail-exam";
    }
    
    @PostMapping(value = { "/cancel-exam" })
    public String cancelExam(Long examId, Model model) {
        model.addAttribute("exam", examService.cancelExam(examId));
        model.addAttribute("messageSuccess", "Hủy bỏ kì thi hoàn tất.");
        return "detail-exam";
    }

    @GetMapping(value = {"/update-exam"})
    public String visitUpdateExamPage(Long examId, Model model) {
        model.addAttribute("examUpdateForm", examService.getObjectUpdate(examId));
        return "update-exam";
    }

    @PostMapping(value = { "/update-exam" })
    public String saveExam(@ModelAttribute("examForm") ExamUpdateForm examUpdateForm, Model model) {
        model.addAttribute("exam", examService.updateExam(examUpdateForm));
        model.addAttribute("messageSuccess", "Chỉnh sửa kỳ thi hoàn tất.");
        return "detail-exam";
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
    
    @GetMapping(value = {"/review-exam-info"})
    public String visitReviewExamInfoPage(Model model) {
        return "review-exam-info";
    }
}
