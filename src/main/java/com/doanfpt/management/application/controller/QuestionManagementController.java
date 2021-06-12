package com.doanfpt.management.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doanfpt.management.application.dto.ExamForm;
import com.doanfpt.management.application.dto.QuestionForm;
import com.doanfpt.management.application.entities.Question;
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
    
    @GetMapping(value = {"/question"})
    public String visitQuestionPage(Model model) {
        return "question-management";
    }
    
    @GetMapping(value = {"/create-question"})
    public String createQuestion(Model model) {
        QuestionForm questionForm = new QuestionForm();
        model.addAttribute(questionForm);
        return "create-question";
    }
    
    @PostMapping(value = {"/save-question"})
    public String createNewQuestion(@ModelAttribute QuestionForm form, Model model) {
        questionServices.createNewQuestion(form);
        QuestionForm questionForm = new QuestionForm();
        model.addAttribute(questionForm);
        return "create-question";
    }
    
    @GetMapping(value = {"/create-exam"})
    public String createNewExam(Model model) {
        ExamForm examForm = new ExamForm();
        List<Question> listQuestions = questionServices.getAllQuestions();
        model.addAttribute("listQuestions", listQuestions);
        model.addAttribute("examForm", examForm);
        return "create-exam";
    }
    
    @PostMapping(value = {"/save-exam"})
    public String createNewExam(@ModelAttribute ExamForm form, Model model) {
        return "redirect:/create-exam";
    }
}
