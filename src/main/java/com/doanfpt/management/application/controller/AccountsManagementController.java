package com.doanfpt.management.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doanfpt.management.application.dto.AccountForm;
import com.doanfpt.management.application.dto.FormSearchAccount;
import com.doanfpt.management.application.services.AccountServices;
import com.doanfpt.management.application.services.AddressServices;
import com.doanfpt.management.application.services.RoleServices;
import com.doanfpt.management.application.validator.AccountFormValidator;

@Controller
@RequestMapping("/management")
public class AccountsManagementController {

    @Autowired
    AccountServices accountsServices;

    @Autowired
    RoleServices roleServices;

    @Autowired
    private AccountFormValidator accountFormValidator;

    @Autowired
    AddressServices addressServices;

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        // Form mục tiêu
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        if (target.getClass() == AccountForm.class) {
            dataBinder.setValidator(accountFormValidator);
        }
    }

    @GetMapping(value = { "/account" })
    public String visitAccountPage(Model model) {
        model.addAttribute("listAccount", accountsServices.findAllAccount());
        model.addAttribute("formSearchAccount", new FormSearchAccount());
        return "account-management";
    }

    @PostMapping(value = { "/search-account" })
    public String searchAccount(FormSearchAccount formSearchAccount, Model model) {
        model.addAttribute("listAccount", accountsServices.searchAccount(formSearchAccount));
        model.addAttribute("formSearchAccount", formSearchAccount);
        model.addAttribute("isSearch", true);
        return "account-management";
    }

    @GetMapping(value = { "/view-profile" })
    public String viewProfile(Model model) {
        model.addAttribute("account", accountsServices.getAccountLogin());
        return "view-profile";
    }

    @GetMapping(value = { "/create-account" })
    public String visitPageCreateAccount(Model model) {
        AccountForm accountForm = new AccountForm();
        model.addAttribute("accountForm", accountForm);
        model.addAttribute("listRole", roleServices.findAllRole());
        return "create-account";
    }

    @PostMapping(value = { "/save-account" })
    public String saveAccount(@Validated AccountForm accountForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("accountForm", accountForm);
        } else {
            model.addAttribute("messageSuccess", "Thêm người dùng thành công!");
            model.addAttribute("accountForm", new AccountForm());
            accountsServices.createAccount(accountForm);
        }
        model.addAttribute("listRole", roleServices.findAllRole());
        return "create-account";
    }

    @PostMapping(value = { "/update-account" })
    public String updateAccount(AccountForm accountForm, Model model) {
        boolean updateSuccess = accountsServices.updateAccount(accountForm);
        if (updateSuccess) {
            model.addAttribute("messageSuccess", "Cập nhật thông tin thành công!");
        } else {
            model.addAttribute("messageError", "Quá trình cập nhật thất bại!");
        }
        model.addAttribute("accountForm", accountForm);
        return "edit-account";
    }

    @RequestMapping("/edit-account")
    public String showEditAccountForm(Long accountId, Model model) {
        model.addAttribute("accountForm", accountsServices.getObjectUpdate(accountId));
        model.addAttribute("listProvince", addressServices.getAllProvince());
        return "edit-account";
    }

    @GetMapping(value = { "/delete-account" })
    public String viewProfile(Long accountId, Model model) {
        accountsServices.deleteAccount(accountId);
        model.addAttribute("listAccount", accountsServices.findAllAccount());
        model.addAttribute("formSearchAccount", new FormSearchAccount());
        return "account-management";
    }
}
