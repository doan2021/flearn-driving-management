package com.flearndriving.management.application.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.flearndriving.management.application.controller.ExamManagementController;

@ControllerAdvice(assignableTypes = { ExamManagementController.class })
public class ExamManagementControllerAdvice {

    @ModelAttribute("classActiveExamTab")
    public String cssActivePage() {
        return "active";
    }
}
