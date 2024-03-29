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
import com.flearndriving.management.application.dto.request.DrivingLicenseForm;
import com.flearndriving.management.application.dto.request.ExamQuestionsForm;
import com.flearndriving.management.application.dto.request.FormSearchDrivingLicense;
import com.flearndriving.management.application.services.impl.ChapterServicesImpl;
import com.flearndriving.management.application.services.DrivingLicenseServices;
import com.flearndriving.management.application.services.ExamQuestionsServices;
import com.flearndriving.management.application.services.QuestionServices;
import com.flearndriving.management.application.validator.CreateDrivingLicenseValidator;
import com.flearndriving.management.application.validator.CreateExamQuestionValidator;

@Controller
@RequestMapping("/management")
public class DrivingLicenseManagementController {

    @Autowired
    ChapterServicesImpl chapterServices;

    @Autowired
    DrivingLicenseServices drivingLicenseServices;

    @Autowired
    CreateDrivingLicenseValidator createDrivingLicenseValidator;

    @Autowired
    ExamQuestionsServices examQuestionsServices;
    
    @Autowired
    QuestionServices questionServices;

    @Autowired
    CreateExamQuestionValidator createExamQuestionValidator;

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        // Init validate
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        if (target.getClass() == DrivingLicenseForm.class) {
            dataBinder.setValidator(createDrivingLicenseValidator);
        }
        if (target.getClass() == ExamQuestionsForm.class) {
            dataBinder.setValidator(createExamQuestionValidator);
        }
    }

    @GetMapping(value = { "/driving-license" })
    public String visitDrivingLicensePage(FormSearchDrivingLicense formSearchDrivingLicense, Model model) {
        model.addAttribute(Constant.PAGE_CONTENT_NAME,
                drivingLicenseServices.searchDrivingLicense(formSearchDrivingLicense));
        model.addAttribute("formSearchDrivingLicense", new FormSearchDrivingLicense());
        return "driving-license-management";
    }

    @PostMapping(value = { "/search-driving-license" })
    public String searchDrivingLicense(FormSearchDrivingLicense formSearchDrivingLicense, Model model) {
        model.addAttribute(Constant.PAGE_CONTENT_NAME,
                drivingLicenseServices.searchDrivingLicense(formSearchDrivingLicense));
        model.addAttribute("formSearchDrivingLicense", formSearchDrivingLicense);
        return "driving-license-management";
    }

    @GetMapping(value = { "/create-driving-license" })
    public String visitCreateDrivingLicenseManagementPage(Model model) {
        DrivingLicenseForm drivingLicenseForm = new DrivingLicenseForm();
        model.addAttribute("drivingLicenseForm", drivingLicenseForm);
        model.addAttribute("listChapter", chapterServices.findAllChapter());
        return "create-driving-license";
    }

    @PostMapping(value = { "/create-driving-license" })
    public String createDrivingLicense(@Validated DrivingLicenseForm drivingLicenseForm, BindingResult result,
            Model model) {
        model.addAttribute("listChapter", chapterServices.findAllChapter());
        if (result.hasErrors()) {
            return "create-driving-license";
        }
        drivingLicenseServices.createDrivingLicense(drivingLicenseForm);
        model.addAttribute(Constant.STATUS_SUCCESS, "Tạo hạng bằng lái thành công!");
        model.addAttribute("drivingLicenseForm", new DrivingLicenseForm());
        return "create-driving-license";
    }

    @GetMapping(value = { "/detail-driving-license" })
    public String visitDetailDrivingLicenseManagementPage(Long drivingLicenseId, Model model) {
        model.addAttribute("drivingLicense", drivingLicenseServices.findById(drivingLicenseId));
        model.addAttribute("listExamQuestions", examQuestionsServices.findExamQuestionByDrivingLicenseId(drivingLicenseId));
        return "detail-driving-license";
    }

    @PostMapping(value = { "/update-driving-license" })
    public String updateDrivingLicense(@Validated DrivingLicenseForm drivingLicenseForm, BindingResult result,
            Model model) {
        model.addAttribute("listChapter", chapterServices.findAllChapter());
        if (result.hasErrors()) {
            return "update-driving-license";
        }
//        drivingLicenseServices.updateDrivingLicense(drivingLicenseForm);
        model.addAttribute(Constant.STATUS_SUCCESS, "Chỉnh sửa hạng bằng lái thành công!");
        model.addAttribute("drivingLicenseForm",
                drivingLicenseServices.getObjectUpdate(drivingLicenseForm.getDrivingLicenseId()));
        return "update-driving-license";
    }

    @GetMapping(value = { "/update-driving-license" })
    public String updateDrivingLicense(Model model, Long drivingLicenseId) {
        model.addAttribute("listChapter", chapterServices.findAllChapter());
        model.addAttribute("listQuestion", questionServices.getQuestionInChapter(drivingLicenseId));
        model.addAttribute("drivingLicenseForm", drivingLicenseServices.getObjectUpdate(drivingLicenseId));
        return "update-driving-license";
    }

    @PostMapping(value = { "/delete-driving-license" })
    public String updateDrivingLicense(Long drivingLicenseId, RedirectAttributes redirAttrs) {
        drivingLicenseServices.deleteDrivingLicense(drivingLicenseId);
        redirAttrs.addFlashAttribute(Constant.STATUS_SUCCESS, "Xóa hạng bằng thành công!");
        return "redirect:driving-license";
    }

    @GetMapping(value = { "/create-exam-questions" })
    public String visitCreateExamQuestionPage(Long drivingLicenseId, Model model) {
        ExamQuestionsForm examQuestionsForm = new ExamQuestionsForm();
        model.addAttribute("examQuestionsForm", examQuestionsForm);
        model.addAttribute("drivingLicense", drivingLicenseServices.findById(drivingLicenseId));
        model.addAttribute("listQuestionParalysis", examQuestionsServices.findQuestionParalysis());
        return "create-exam-questions";
    }

    @PostMapping(value = { "/create-exam-questions" })
    public String createExamQuestion(@Validated ExamQuestionsForm examQuestionsForm, BindingResult result, Model model) {
        model.addAttribute("drivingLicense", drivingLicenseServices.findById(examQuestionsForm.getDrivingLicenseId()));
        if (result.hasErrors()) {
            return "create-exam-questions";
        }
        examQuestionsServices.createExamQuestions(examQuestionsForm);
        model.addAttribute(Constant.STATUS_SUCCESS, "Tạo đề thi thành công!");
        model.addAttribute("examQuestionsForm", new ExamQuestionsForm());
        return "create-exam-questions";
    }
    
    @GetMapping(value = { "/detail-exam-questions" })
    public String visitDetailExamQuestionPage(Long examQuestionsId, Model model) {
        model.addAttribute("examQuestions", examQuestionsServices.findByExamQuestionId(examQuestionsId));
        model.addAttribute("listQuestions", examQuestionsServices.findQuestionInExamQuestions(examQuestionsId));
        return "detail-exam-questions";
    }
    
    @PostMapping(value = { "/delete-exam-question" })
    public String deleteDrivingLicense(Long examQuestionsId, RedirectAttributes redirAttrs) {
        Long drivingLicenseId = examQuestionsServices.deleteExamQuestions(examQuestionsId);
        redirAttrs.addFlashAttribute(Constant.STATUS_SUCCESS, "Xóa đề thi công!");
        return "redirect:detail-driving-license?drivingLicenseId=" + drivingLicenseId;
    }
}
