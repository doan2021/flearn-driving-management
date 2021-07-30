package com.doanfpt.management.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doanfpt.management.application.dto.DrivingLicenseForm;
import com.doanfpt.management.application.services.ChapterServices;

@Controller
@RequestMapping("/management")
public class DrivingLicenceManagementController {
    
    @Autowired
    ChapterServices chapterServices;

    @GetMapping(value = { "/driving-license" })
    public String visitDrivingLicenseManagementPage(Model model) {
        return "driving-license-management";
    }
    
    @GetMapping(value = { "/create-driving-license" })
    public String visitCreateDrivingLicenseManagementPage(Model model) {
        DrivingLicenseForm drivingLicenseForm = new DrivingLicenseForm();
        model.addAttribute("drivingLicenseForm", drivingLicenseForm);
        model.addAttribute("listChapter", chapterServices.findAllChapter());
        return "create-driving-license";
    }
}
