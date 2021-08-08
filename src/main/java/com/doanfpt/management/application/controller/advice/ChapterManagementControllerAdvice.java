package com.doanfpt.management.application.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.doanfpt.management.application.controller.ChapterManagementController;

@ControllerAdvice(assignableTypes = { ChapterManagementController.class })
public class ChapterManagementControllerAdvice {

    @ModelAttribute("classActiveChapterTab")
    public String cssActivePage() {
        return "active";
    }
}
