package com.flearndriving.management.application.controller.advice;

import com.flearndriving.management.application.controller.CustomerManagementController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(assignableTypes = { CustomerManagementController.class })
public class CustomerManagementControllerAdvice {

    @ModelAttribute("classActiveCustomerTab")
    public String cssActivePage() {
        return "active";
    }
}
