package com.flearndriving.management.application.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.flearndriving.management.application.controller.DrivingLicenseManagementController;

@ControllerAdvice(assignableTypes = { DrivingLicenseManagementController.class })
public class DrivingLicenseManagementControllerAdvice {

    @ModelAttribute("classActiveDrivingLicenseTab")
    public String cssActivePage() {
        return "active";
    }
}
