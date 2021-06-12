package com.doanfpt.management.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management")
public class ExamManagementController {
    
    @GetMapping(value = {"/exam"})
    public String visitExamPage(Model model) {
        return "exam-management";
    }
    
    @GetMapping(value = {"/create-exam"})
    public String createExamPage(Model model) {
        return "create-exam";
    }
}
