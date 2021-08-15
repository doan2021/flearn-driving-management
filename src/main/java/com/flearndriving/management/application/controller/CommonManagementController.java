package com.flearndriving.management.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonManagementController {

    @GetMapping(value = { "/", "/login" })
    public String visitPageLogin(Model model) {
        return "login-management";
    }
}
