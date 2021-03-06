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
import com.flearndriving.management.application.dto.ChapterForm;
import com.flearndriving.management.application.dto.FormSearchChapter;
import com.flearndriving.management.application.dto.QuestionForm;
import com.flearndriving.management.application.services.ChapterServices;
import com.flearndriving.management.application.services.QuestionServices;
import com.flearndriving.management.application.validator.ChapterCreateValidator;
import com.flearndriving.management.application.validator.CreateQuestionValidator;

@Controller
@RequestMapping("/management")
public class ChapterManagementController {

    @Autowired
    ChapterServices chapterServices;

    @Autowired
    QuestionServices questionServices;

    @Autowired
    ChapterCreateValidator chapterCreateValidator;

    @Autowired
    CreateQuestionValidator createQuestionValidator;

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        if (target.getClass() == ChapterForm.class) {
            dataBinder.setValidator(chapterCreateValidator);
        }
        if (target.getClass() == QuestionForm.class) {
            dataBinder.setValidator(createQuestionValidator);
        }
    }

    @GetMapping(value = { "/chapter" })
    public String visitChapterPage(FormSearchChapter formSearchChapter, Model model) {
        model.addAttribute(Constant.PAGE_CONTENT_NAME, chapterServices.searchChapter(formSearchChapter));
        model.addAttribute("formSearchChapter", new FormSearchChapter());
        return "chapter-management";
    }

    @PostMapping(value = { "/search-chapter" })
    public String searchChapter(FormSearchChapter formSearchChapter, Model model) {
        model.addAttribute(Constant.PAGE_CONTENT_NAME, chapterServices.searchChapter(formSearchChapter));
        model.addAttribute("formSearchChapter", formSearchChapter);
        return "chapter-management";
    }

    @GetMapping(value = { "/chapter-detail" })
    public String visitChapterDetailPage(Long chapterId, Model model) {
        model.addAttribute("chapter", chapterServices.getChapterDetail(chapterId));
        model.addAttribute("listQuestions", questionServices.getQuestionInChapter(chapterId));
        return "chapter-detail";
    }

    @GetMapping(value = { "/create-chapter" })
    public String createChapter(Model model) {
        ChapterForm chapterForm = new ChapterForm();
        model.addAttribute("chapterForm", chapterForm);
        return "create-chapter";
    }

    @PostMapping(value = { "/create-chapter" })
    public String saveChapter(@Validated ChapterForm chapterForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create-chapter";
        }
        chapterServices.createChapter(chapterForm);
        model.addAttribute(Constant.STATUS_SUCCESS, "T???o ch????ng m???i th??nh c??ng!");
        model.addAttribute("chapterForm", new ChapterForm());
        return "create-chapter";
    }

    @GetMapping(value = { "/update-chapter" })
    public String editPageChapterDetail(Long chapterId, Model model) {
        model.addAttribute("chapterForm", chapterServices.getObjectUpdate(chapterId));
        model.addAttribute("chapter", chapterServices.getChapterDetail(chapterId));
        return "update-chapter";
    }

    @PostMapping(value = { "/update-chapter" })
    public String editChapterDetail(@Validated ChapterForm chapterForm, BindingResult result, RedirectAttributes redirAttrs) {
        if (result.hasErrors()) {
            return "update-chapter";
        }
        chapterServices.updateChapter(chapterForm);
        redirAttrs.addFlashAttribute(Constant.STATUS_SUCCESS, "Ch???nh s???a ch????ng th??nh c??ng!");
        return "redirect:chapter-detail?chapterId=" + chapterForm.getChapterId();
    }

    @PostMapping(value = { "/delete-chapter" })
    public String deleteChapter(Long chapterId, RedirectAttributes redirAttrs) {
        chapterServices.deleteChapter(chapterId);
        redirAttrs.addFlashAttribute(Constant.STATUS_SUCCESS, "X??a ch????ng th??nh c??ng!");
        return "redirect:chapter";
    }

    @GetMapping(value = { "/create-question" })
    public String createQuestion(Long chapterId, Model model) {
        QuestionForm questionForm = new QuestionForm();
        model.addAttribute("questionForm", questionForm);
        model.addAttribute("chapter", chapterServices.getChapterDetail(chapterId));
        return "create-question";
    }

    @PostMapping(value = { "/save-question" })
    public String saveQuestion(@Validated QuestionForm questionForm, BindingResult result, Model model) {
        model.addAttribute("chapter", chapterServices.getChapterDetail(questionForm.getChapterId()));
        if (result.hasErrors()) {
            return "create-question";
        }
        questionServices.createQuestion(questionForm);
        model.addAttribute("questionForm", new QuestionForm());
        model.addAttribute(Constant.STATUS_SUCCESS, "T???o c??u h???i th??nh c??ng!");
        return "create-question";
    }

    @GetMapping(value = { "/detail-question" })
    public String visitDetailQuestionPage(Long questionId, Model model) {
        model.addAttribute("question", questionServices.getOneQuestion(questionId));
        return "detail-question";
    }

    @PostMapping(value = { "/delete-question" })
    public String deleteQuestion(Long questionId, Long chapterId, RedirectAttributes redirAttrs) {
        questionServices.deleteQuestion(questionId);
        redirAttrs.addFlashAttribute(Constant.STATUS_SUCCESS, "X??a c??u h???i th??nh c??ng!");
        return "redirect:chapter-detail?chapterId=" + chapterId;
    }
}
