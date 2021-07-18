package com.doanfpt.management.application.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.doanfpt.management.application.exception.BusinessException;

@ControllerAdvice
public class ExceptionManagementController {
    
    @ExceptionHandler(BusinessException.class)
    public String handleApplicationException(BusinessException e, Model model) {
        model.addAttribute("error", e);
        return "business-error";
    }

    @ExceptionHandler(Exception.class)
    public String handleUnwantedException(Exception e, Model model) {
        model.addAttribute("error", e);
        return "system-error";
    }
}
