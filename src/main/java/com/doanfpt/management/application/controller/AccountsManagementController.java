package com.doanfpt.management.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doanfpt.management.application.services.AccountServices;
import com.doanfpt.management.application.services.RoleServices;

@Controller
@RequestMapping("/management")
public class AccountsManagementController {

    @Autowired
    AccountServices accountsServices;

    @Autowired
    RoleServices roleServices;
    
    @GetMapping(value = { "/account" })
    public String visitAccountPage(Model model) {
        return "account-management";
    }
    
    @GetMapping(value = { "/create-account" })
    public String visitCreateAccountPage(Model model) {
        return "create-account";
    }
    
    @GetMapping(value = { "/view-profile" })
    public String viewProfile(Model model) {
        model.addAttribute("account", accountsServices.getAccountLogin());
        return "view-profile";
    }
}
