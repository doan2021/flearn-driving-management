package com.doanfpt.management.application.controller;

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

import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.dto.ExamQuestionsForm;
import com.doanfpt.management.application.services.DrivingLicenseServices;
import com.doanfpt.management.application.services.ExamQuestionsServices;
import com.doanfpt.management.application.services.QuestionServices;
import com.doanfpt.management.application.validator.CreateExamQuestionValidator;

@Controller
@RequestMapping("/management")
public class ExamQuestionsManagementController {

    @Autowired
    QuestionServices questionServices;

    @Autowired
    ExamQuestionsServices examQuestionsServices;

    @Autowired
    DrivingLicenseServices drivingLicenseServices;

    @Autowired
    CreateExamQuestionValidator createExamQuestionValidator;

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        // Form mục tiêu
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        if (target.getClass() == ExamQuestionsForm.class) {
            dataBinder.setValidator(createExamQuestionValidator);
        }
    }

    @GetMapping(value = { "/create-exam-questions" })
    public String visitCreateExamQuestionPage(Long drivingLicenseId, Model model) {
        ExamQuestionsForm examQuestionsForm = new ExamQuestionsForm();
        model.addAttribute("examQuestionsForm", examQuestionsForm);
        model.addAttribute("drivingLicense", drivingLicenseServices.findById(drivingLicenseId));
        return "create-exam-questions";
    }

    @PostMapping(value = { "/create-exam-questions" })
    public String createExamQuestion(@Validated ExamQuestionsForm examQuestionsForm, BindingResult result,
            Model model, RedirectAttributes redirAttrs) {
        model.addAttribute("drivingLicense", drivingLicenseServices.findById(examQuestionsForm.getDrivingLicenseId()));
        if (result.hasErrors()) {
            return "create-exam-questions";
        }
        examQuestionsServices.createExamQuestions(examQuestionsForm);
        redirAttrs.addFlashAttribute(Constant.STATUS_SUCCESS, "Tạo đề thi thành công!");
        return "redirect:create-exam-questions?drivingLicenseId=" + examQuestionsForm.getDrivingLicenseId();
    }
}
