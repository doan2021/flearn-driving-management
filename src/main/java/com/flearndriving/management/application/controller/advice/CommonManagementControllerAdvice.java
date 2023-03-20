package com.flearndriving.management.application.controller.advice;

import com.flearndriving.management.application.dto.CustomerLogin;
import com.flearndriving.management.application.services.CustomerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(annotations = {Controller.class})
public class CommonManagementControllerAdvice {
    
    @Autowired
    CustomerServices customerServices;

    @ModelAttribute("customerLogin")
    public CustomerLogin loadCustomerLogin() {
        return customerServices.getBasicInfocustomerLogin();
    }
}
 