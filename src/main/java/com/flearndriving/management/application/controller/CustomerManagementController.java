package com.flearndriving.management.application.controller;

import com.flearndriving.management.application.common.Constant;
import com.flearndriving.management.application.dto.request.CustomerRequest;
import com.flearndriving.management.application.dto.request.CustomerUpdateForm;
import com.flearndriving.management.application.dto.request.FormSearchCustomer;
import com.flearndriving.management.application.services.CustomerServices;
import com.flearndriving.management.application.services.RoleServices;
import com.flearndriving.management.application.validator.CustomerFormValidator;
import com.flearndriving.management.application.validator.CustomerUpdateValidator;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/management")
public class CustomerManagementController {

    @Autowired
    CustomerServices customerServices;

    @Autowired
    RoleServices roleServices;

    @Autowired
    private CustomerFormValidator customerFormValidator;

    @Autowired
    private CustomerUpdateValidator customerUpdateValidator;

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        // Form mục tiêu
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        if (target.getClass() == CustomerRequest.class) {
            dataBinder.setValidator(customerFormValidator);
        }
        if (target.getClass() == CustomerUpdateForm.class) {
            dataBinder.setValidator(customerUpdateValidator);
        }
    }

    @GetMapping(value = {"/customer"})
    public String visitCustomerPage(FormSearchCustomer formSearchcustomer, Model model) {
        model.addAttribute(Constant.PAGE_CONTENT_NAME, customerServices.searchCustomer(formSearchcustomer));
        model.addAttribute("formSearchcustomer", new FormSearchCustomer());
        return "customer-management";
    }

    @PostMapping(value = {"/search-customer"})
    public String searchCustomer(FormSearchCustomer formSearchcustomer, Model model) {
        model.addAttribute(Constant.PAGE_CONTENT_NAME, customerServices.searchCustomer(formSearchcustomer));
        model.addAttribute("formSearchcustomer", formSearchcustomer);
        return "customer-management";
    }

    @GetMapping(value = {"/view-profile"})
    public String viewProfile(Model model) {
        model.addAttribute("customerUpdateForm", customerServices.getCustomerLoginInfo());
        return "view-profile";
    }

    @PostMapping(value = {"/update-customer-view"})
    public String updateCustomerView(@Validated CustomerUpdateForm customerUpdateForm, BindingResult result,
                                     RedirectAttributes redirAttrs) {
        if (result.hasErrors()) {
            return "view-profile";
        }
        customerServices.updateCustomer(customerUpdateForm);
        redirAttrs.addFlashAttribute(Constant.STATUS_SUCCESS, "Cập nhật thông tin thành công!");
        return "redirect:view-profile";
    }

    @GetMapping(value = {"/create-customer"})
    public String visitPageCreateCustomer(Model model) {
        CustomerRequest customerForm = new CustomerRequest();
        model.addAttribute("customerForm", customerForm);
        model.addAttribute("listRole", roleServices.findAllRole());
        return "create-customer";
    }

    @PostMapping(value = {"/create-customer"})
    public String saveCustomer(@Validated CustomerRequest customerForm, BindingResult result, Model model) {
        model.addAttribute("listRole", roleServices.findAllRole());
        if (result.hasErrors()) {
            return "create-customer";
        }
        model.addAttribute(Constant.STATUS_SUCCESS, "Tạo người dùng thành công!");
        model.addAttribute("customerForm", new CustomerRequest());
        customerServices.createCustomer(customerForm);
        return "create-customer";
    }

    @GetMapping("/update-customer")
    public String showEditCustomerForm(Long customerId, Model model) {
        model.addAttribute("customerUpdateForm", customerServices.getObjectUpdate(customerId));
        return "update-customer";
    }

    @PostMapping(value = {"/update-customer"})
    public String updateCustomer(@Validated CustomerUpdateForm customerUpdateForm, BindingResult result, RedirectAttributes redirAttrs) {
        if (result.hasErrors()) {
            return "update-customer";
        }
        customerServices.updateCustomer(customerUpdateForm);
        redirAttrs.addFlashAttribute(Constant.STATUS_SUCCESS, "Cập nhật thông tin thành công!");
        return "redirect:update-customer?customerId=" + customerUpdateForm.getCustomerId();
    }

    @PostMapping(value = {"/delete-customer"})
    public String viewProfile(Long customerId, RedirectAttributes redirAttrs) {
        customerServices.deleteCustomer(customerId);
        redirAttrs.addFlashAttribute(Constant.STATUS_SUCCESS, "Xóa tài khoản thành công!");
        return "redirect:customer";
    }

    @PostMapping(value = {"/upload-avatar"})
    public String uploadAvatar(MultipartFile data, Long customerId, RedirectAttributes redirAttrs) {
        customerServices.uploadAvatar(data, customerId);
        redirAttrs.addFlashAttribute(Constant.STATUS_SUCCESS, "Sửa ảnh đại diện thành công!");
        return "redirect:view-profile";
    }

    @PostMapping(value = {"/upload-avatar-customer"})
    public String uploadAvatarcustomer(MultipartFile data, Long customerId, RedirectAttributes redirAttrs) {
        customerServices.uploadAvatar(data, customerId);
        redirAttrs.addFlashAttribute(Constant.STATUS_SUCCESS, "Sửa ảnh đại diện thành công!");
        return "redirect:update-customer?customerId=" + customerId;
    }
}
