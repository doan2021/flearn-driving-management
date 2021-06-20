package com.doanfpt.management.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management")
public class DashboardManagementController {

    @GetMapping(value = { "/dashboard" })
    public String init(Model model) {
        return "dashboard";
    }
}
