package com.flearndriving.management.application.controller.advice;

import com.flearndriving.management.application.dto.request.CustomerLogin;
import com.flearndriving.management.application.services.CustomerServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;

@ControllerAdvice(annotations = {Controller.class})
public class CommonManagementControllerAdvice {

    @Resource
    CustomerServices customerServices;

    @ModelAttribute("customerLogin")
    public CustomerLogin loadCustomerLogin() {
        return customerServices.getCustomerLoginDetail();
    }
}
 