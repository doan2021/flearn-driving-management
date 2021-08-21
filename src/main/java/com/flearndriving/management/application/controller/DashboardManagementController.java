package com.flearndriving.management.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flearndriving.management.application.services.AccountServices;
import com.flearndriving.management.application.services.ChapterServices;
import com.flearndriving.management.application.services.DrivingLicenseInfoServices;
import com.flearndriving.management.application.services.QuestionServices;

@Controller
@RequestMapping("/management")
public class DashboardManagementController {

    @Autowired
    AccountServices accountServices;

    @Autowired
    ChapterServices chapterServices;

    @Autowired
    QuestionServices questionServices;

    @Autowired
    DrivingLicenseInfoServices drivingLicenseInfoServices;

    @GetMapping(value = { "/dashboard" })
    public String visitDashboardPage(Model model) {
        model.addAttribute("numberOfChapter", chapterServices.countChapter());
        model.addAttribute("numberOfAccount", accountServices.countAccount());
        model.addAttribute("numberOfQuestion", questionServices.countQuestion());
        model.addAttribute("numberOfDrivingLicenseInfo", drivingLicenseInfoServices.countDrivingLicenseInfo());
        model.addAttribute("listAccount", accountServices.getDataReportAccountMonth());
        return "dashboard";
    }

    @GetMapping("/export-report-account")
    public String visitReportAccount(Model model) {
        model.addAttribute("listAccount", accountServices.getDataReportAccountMonth());
        model.addAttribute("owner", accountServices.getAccountLoginInfo());
        return "account-report";
    }
    
    @GetMapping("/export-report-account-one-month")
    public String visitReportAccountOneMonth(Model model) {
        model.addAttribute("listAccount", accountServices.getDataReportAccountOneMonth());
        model.addAttribute("owner", accountServices.getAccountLoginInfo());
        return "account-report";
    }
}
