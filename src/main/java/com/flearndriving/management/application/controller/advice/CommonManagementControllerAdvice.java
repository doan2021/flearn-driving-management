package com.flearndriving.management.application.controller.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.flearndriving.management.application.dto.AccountLogin;
import com.flearndriving.management.application.services.AccountServices;

@ControllerAdvice(annotations = {Controller.class})
public class CommonManagementControllerAdvice {
    
    @Autowired
    AccountServices accountServices;

    @ModelAttribute("accountLogin")
    public AccountLogin loadAccountLogin() {
        return accountServices.getBasicInfoAccountLogin();
    }
}
 