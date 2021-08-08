package com.doanfpt.management.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.dto.ExamForm;
import com.doanfpt.management.application.dto.ExamUpdateForm;
import com.doanfpt.management.application.dto.FormSearchExam;
import com.doanfpt.management.application.services.DrivingLicenseServices;
import com.doanfpt.management.application.services.ExamServices;
import com.doanfpt.management.application.validator.ExamCreateValidator;

@Controller
@RequestMapping("/management")
public class ExamManagementController {

    @Autowired
    private ExamServices examService;
    
    @Autowired
    private DrivingLicenseServices drivingLicenseServices;
    
    @Autowired
    private ExamCreateValidator examCreateValidator;
    
    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        if (target.getClass() == ExamForm.class) {
            dataBinder.setValidator(examCreateValidator);
        }
    }

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
    public String createExam(@ModelAttribute("examForm") ExamForm examForm, Model model, BindingResult result) {
    	if (result.hasErrors()) {
            return "create-exam";
        }
    	examService.createExam(examForm);
    	model.addAttribute(Constant.STATUS_SUCCESS, "Thêm kỳ thi mới thành công!");
    	model.addAttribute("examForm", new ExamForm());
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
    public String saveExam(@ModelAttribute("examForm") ExamUpdateForm examUpdateForm, RedirectAttributes redirAttrs) {
        examService.updateExam(examUpdateForm);
    	redirAttrs.addFlashAttribute(Constant.STATUS_SUCCESS, "Chỉnh sửa kỳ thi thành công!");
        return "redirect:detail-exam?examId=" + examUpdateForm.getExamId();
    }

    @PostMapping(value = { "/search-exam" })
    public String searchExam(FormSearchExam formSearchExam, Model model) {
        model.addAttribute(Constant.PAGE_CONTENT_NAME, examService.searchExam(formSearchExam));
        model.addAttribute("formSearchExam", formSearchExam);
        return "exam-management";
    }

    @PostMapping(value = { "/delete-exam" })
    public String deleteExam(Long examId, RedirectAttributes redirAttrs) {
        examService.deleteExam(examId);
        redirAttrs.addFlashAttribute(Constant.STATUS_SUCCESS, "Xóa kỳ thi thành công!");
        return "redirect:exam";
    }
    
    @GetMapping(value = {"/review-exam-info"})
    public String visitReviewExamInfoPage(Model model) {
        return "review-exam-info";
    }
}
