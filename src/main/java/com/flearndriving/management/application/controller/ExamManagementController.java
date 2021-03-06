package com.flearndriving.management.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.flearndriving.management.application.common.Constant;
import com.flearndriving.management.application.dto.ExamForm;
import com.flearndriving.management.application.dto.ExamUpdateForm;
import com.flearndriving.management.application.dto.FormSearchExam;
import com.flearndriving.management.application.services.DrivingLicenseServices;
import com.flearndriving.management.application.services.ExamServices;
import com.flearndriving.management.application.validator.ExamCreateValidator;
import com.flearndriving.management.application.validator.ExamUpdateValidator;

@Controller
@RequestMapping("/management")
public class ExamManagementController {

    @Autowired
    private ExamServices examService;

    @Autowired
    private DrivingLicenseServices drivingLicenseServices;

    @Autowired
    private ExamCreateValidator examCreateValidator;
    
    @Autowired
    private ExamUpdateValidator examUpdateValidator;

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        if (target.getClass() == ExamForm.class) {
            dataBinder.setValidator(examCreateValidator);
        }
        if (target.getClass() == ExamUpdateForm.class) {
            dataBinder.setValidator(examUpdateValidator);
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
    public String createExam(@Validated ExamForm examForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create-exam";
        }
        model.addAttribute("listDrivingLicense", drivingLicenseServices.findAllDrivingLicense());
        model.addAttribute(Constant.STATUS_SUCCESS, "Th??m k??? thi m???i th??nh c??ng!");
        model.addAttribute("examForm", new ExamForm());
        examService.createExam(examForm);
        return "create-exam";
    }

    @GetMapping(value = { "/detail-exam" })
    public String visitDetailExamPage(Long examId, Model model) {
        model.addAttribute("exam", examService.getOne(examId));
        return "detail-exam";
    }

    @PostMapping(value = { "/cancel-exam" })
    public String cancelExam(Long examId, Model model) {
        model.addAttribute("exam", examService.cancelExam(examId));
        model.addAttribute("messageSuccess", "H???y b??? k?? thi ho??n t???t.");
        return "detail-exam";
    }

    @GetMapping(value = { "/update-exam" })
    public String visitUpdateExamPage(Long examId, Model model) {
        model.addAttribute("examUpdateForm", examService.getObjectUpdate(examId));
        return "update-exam";
    }

    @PostMapping(value = { "/update-exam" })
    public String saveExam(@Validated ExamUpdateForm examUpdateForm, BindingResult result, RedirectAttributes redirAttrs, Model model) {
    	if (result.hasErrors()) {
            return "update-exam";
        }
    	model.addAttribute("examUpdateForm", examUpdateForm);
    	examService.updateExam(examUpdateForm);
        redirAttrs.addFlashAttribute(Constant.STATUS_SUCCESS, "Ch???nh s???a k??? thi th??nh c??ng!");
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
        redirAttrs.addFlashAttribute(Constant.STATUS_SUCCESS, "X??a k??? thi th??nh c??ng!");
        return "redirect:exam";
    }
}
