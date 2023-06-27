package com.flearndriving.management.application.controller;

import com.flearndriving.management.application.common.Common;
import com.flearndriving.management.application.services.impl.ChapterServicesImpl;
import com.flearndriving.management.application.services.CustomerServices;
import com.flearndriving.management.application.services.QuestionServices;
import com.flearndriving.management.application.services.TrialExamResultService;
import com.flearndriving.management.application.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management")
public class DashboardManagementController {

    @Autowired
    CustomerServices customerServices;

    @Autowired
    ChapterServicesImpl chapterServices;

    @Autowired
    QuestionServices questionServices;

    @Autowired
    TrialExamResultService trialExamResultService;

    @GetMapping(value = {"/dashboard"})
    public String visitDashboardPage(Model model) {
        model.addAttribute("numberOfChapter", chapterServices.countChapter());
        model.addAttribute("numberOfcustomer", customerServices.countCustomer());
        model.addAttribute("numberOfQuestion", questionServices.countQuestion());
        model.addAttribute("numberOfTrialExamResult", trialExamResultService.countTrialExamResult());
        model.addAttribute("listTrialExam", trialExamResultService.getDataReportTrialExamMonth());
        model.addAttribute("listcustomer", customerServices.getDataReportCustomerMonth());
        return "dashboard";
    }

    @GetMapping("/export-report-customer")
    public String visitReportcustomer(Model model) {
        model.addAttribute("listcustomer", customerServices.getDataReportCustomerMonth());
        model.addAttribute("owner", customerServices.getCustomerLoginInfo());
        model.addAttribute("dateStart", DateTimeUtils.getFirstDateOfMonth(DateTimeUtils.plusMonthToDate(Common.getSystemDate(), -2)));
        model.addAttribute("dateEnd", DateTimeUtils.getLastDateOfMonth(DateTimeUtils.plusMonthToDate(Common.getSystemDate(), -1)));
        return "customer-report";
    }

    @GetMapping("/export-report-trial-exam")
    public String visitReportTrialExam(Model model) {
        model.addAttribute("listTrialExam", trialExamResultService.getDataReportTrialExamMonth());
        model.addAttribute("owner", customerServices.getCustomerLoginInfo());
        model.addAttribute("dateStart", DateTimeUtils.getFirstDateOfMonth(DateTimeUtils.plusMonthToDate(Common.getSystemDate(), -2)));
        model.addAttribute("dateEnd", DateTimeUtils.getLastDateOfMonth(DateTimeUtils.plusMonthToDate(Common.getSystemDate(), -1)));
        return "trial-exam-report";
    }
}
