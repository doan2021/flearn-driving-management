package com.doanfpt.management.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doanfpt.management.application.dto.QuestionForm;
import com.doanfpt.management.application.services.ChapterServices;
import com.doanfpt.management.application.services.LearnServices;
import com.doanfpt.management.application.services.QuestionServices;

@Controller
@RequestMapping("/management")
public class QuestionManagementController {
    
    @Autowired
    ChapterServices chapterServices;
    
    @Autowired
    private QuestionServices questionServices;
    
    @Autowired
    LearnServices learnServices;
    
    @GetMapping(value = {"/create-question"})
    public String createQuestion(Long chapterId, Model model) {
        QuestionForm questionForm = new QuestionForm();
        model.addAttribute("questionForm", questionForm);
        model.addAttribute("chapter", chapterServices.getChapterDetail(chapterId));
        return "create-question";
    }
    
    @PostMapping(value = {"/save-question"})
    public String createNewQuestion(@ModelAttribute QuestionForm questionForm, Model model) {
        questionServices.createNewQuestion(questionForm);
        model.addAttribute("questionForm", new QuestionForm());
        model.addAttribute("chapter", chapterServices.getChapterDetail(questionForm.getChapterId()));
        return "create-question";
    }
}
