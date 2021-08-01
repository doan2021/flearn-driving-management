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

import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.dto.QuestionForm;
import com.doanfpt.management.application.services.ChapterServices;
import com.doanfpt.management.application.services.LearnServices;
import com.doanfpt.management.application.services.QuestionServices;
import com.doanfpt.management.application.validator.CreateQuestionValidator;

@Controller
@RequestMapping("/management")
public class QuestionManagementController {
    
    @Autowired
    ChapterServices chapterServices;
    
    @Autowired
    QuestionServices questionServices;
    
    @Autowired
    LearnServices learnServices;
    
    @Autowired
    CreateQuestionValidator createQuestionValidator;
    
    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        if (target.getClass() == QuestionForm.class) {
            dataBinder.setValidator(createQuestionValidator);
        }
    }
    
    @GetMapping(value = {"/create-question"})
    public String createQuestion(Long chapterId, Model model) {
        QuestionForm questionForm = new QuestionForm();
        model.addAttribute("questionForm", questionForm);
        model.addAttribute("chapter", chapterServices.getChapterDetail(chapterId));
        return "create-question";
    }
    
    @PostMapping(value = {"/save-question"})
    public String saveQuestion(@Validated QuestionForm questionForm, BindingResult result, Model model) {
        model.addAttribute("chapter", chapterServices.getChapterDetail(questionForm.getChapterId()));
        if (result.hasErrors()) {
            return "create-question";
        }
        questionServices.createQuestion(questionForm);
        model.addAttribute("questionForm", new QuestionForm());
        model.addAttribute(Constant.STATUS_SUCCESS, "Tạo câu hỏi thành công!");
        return "create-question";
    }
    
    @GetMapping(value = {"/detail-question"})
    public String visitDetailQuestionPage(Long questionId, Model model) {
        model.addAttribute("question", questionServices.getOneQuestion(questionId));
        return "detail-question";
    }
}
