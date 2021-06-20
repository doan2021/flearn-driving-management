package com.doanfpt.management.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.doanfpt.management.application.dto.AccountForm;
import com.doanfpt.management.application.dto.FormSearchAccount;
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
        model.addAttribute("listAccount", accountsServices.findAllAccount());
        model.addAttribute("formSearchAccount", new FormSearchAccount());
        return "account-management";
    }
    
    @GetMapping(value = { "/create-account" })
    public String visitCreateAccountPage(Model model) {
        return "create-account";
    }
  
    @PostMapping(value = {"/search-account"})
    public String searchAccount(FormSearchAccount formSearchAccount,Model model) {
		model.addAttribute("listAccount", accountsServices.searchAccount(formSearchAccount));
        model.addAttribute("formSearchAccount", formSearchAccount);
        model.addAttribute("isSearch", true);
        return "account-management";
    }

    @PostMapping(value = { "/create-account" })
    public String createUser(@ModelAttribute("appUserForm") @Validated AccountForm appUserForm, BindingResult result,
            final RedirectAttributes redirectAttributes, Model model) {
        // Validate result
        if (result.hasErrors()) {
            return "register";
        }
        accountsServices.createAccount(appUserForm);
        return "login";
    }

    @GetMapping(value = { "/view-profile" })
    public String viewProfile(Model model) {
        model.addAttribute("account", accountsServices.getAccountLogin());
        return "view-profile";
    }

    @GetMapping(value = { "/create-account" })
    public String visitPageCreateAccount(Model model) {
        model.addAttribute("account", accountsServices.getAccountLogin());
        return "create-account";
    }

    @GetMapping(value = { "/delete-account" })
    public String viewProfile(Long accountId, Model model) {
        accountsServices.deleteAccount(accountId);
        model.addAttribute("listAccount", accountsServices.findAllAccount());
        model.addAttribute("formSearchAccount", new FormSearchAccount());
        return "account-management";
    }
}
