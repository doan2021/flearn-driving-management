package com.doanfpt.management.application.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.doanfpt.management.application.controller.ExamProfileManagementController;

@ControllerAdvice(assignableTypes = { ExamProfileManagementController.class })
public class ExamProfileManagementControllerAdvice {

    @ModelAttribute("classActiveExamProfileTab")
    public String cssActivePage() {
        return "active";
    }
}
