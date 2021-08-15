package com.flearndriving.management.application.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.flearndriving.management.application.controller.AccountsManagementController;

@ControllerAdvice(assignableTypes = { AccountsManagementController.class })
public class AccountManagementControllerAdvice {

    @ModelAttribute("classActiveAccountTab")
    public String cssActivePage() {
        return "active";
    }
}
