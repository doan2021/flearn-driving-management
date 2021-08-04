package com.doanfpt.management.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doanfpt.management.application.entities.DrivingLicenseInfo;
import com.doanfpt.management.application.services.AccountServices;
import com.doanfpt.management.application.services.ChapterServices;
import com.doanfpt.management.application.services.DrivingLicenseInfoServices;
import com.doanfpt.management.application.services.QuestionServices;

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
		 
		return "dashboard";
	}
}
