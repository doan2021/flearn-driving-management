package com.doanfpt.management.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management")
public class CommonManagementController {
    
    @GetMapping(value = {"/", "/dashboard"})
    public String init(Model model) {
        return "dashboard";
    }
    
    @GetMapping(value = {"/login-management"})
    public String login(Model model) {
        return "login-management";
    }
}
