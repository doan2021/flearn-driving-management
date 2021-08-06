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

import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.dto.DrivingLicenseForm;
import com.doanfpt.management.application.dto.FormSearchDrivingLicense;
import com.doanfpt.management.application.services.ChapterServices;
import com.doanfpt.management.application.services.DrivingLicenseServices;
import com.doanfpt.management.application.validator.CreateDrivingLicenseValidator;

@Controller
@RequestMapping("/management")
public class DrivingLicenceManagementController {

    @Autowired
    ChapterServices chapterServices;
    
    @Autowired
    DrivingLicenseServices drivingLicenseServices;

    @Autowired
    CreateDrivingLicenseValidator createDrivingLicenseValidator;
    
    @GetMapping(value = { "/driving-license" })
    public String visitDrivingLicensePage(FormSearchDrivingLicense formSearchDrivingLicense, Model model) {
        model.addAttribute(Constant.PAGE_CONTENT_NAME,
                drivingLicenseServices.searchDrivingLicense(formSearchDrivingLicense));
        model.addAttribute("formSearchDrivingLicense", new FormSearchDrivingLicense());
        return "driving-license-management";
    }
    
    @PostMapping(value = { "/search-driving-license" })
    public String searchDrivingLicense(FormSearchDrivingLicense formSearchDrivingLicense, Model model) {
        model.addAttribute(Constant.PAGE_CONTENT_NAME,
                drivingLicenseServices.searchDrivingLicense(formSearchDrivingLicense));
        model.addAttribute("formSearchDrivingLicense", formSearchDrivingLicense);
        return "driving-license-management";
    }

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        // Form mục tiêu
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        if (target.getClass() == DrivingLicenseForm.class) {
            dataBinder.setValidator(createDrivingLicenseValidator);
        }
    }

    @GetMapping(value = { "/create-driving-license" })
    public String visitCreateDrivingLicenseManagementPage(Model model) {
        DrivingLicenseForm drivingLicenseForm = new DrivingLicenseForm();
        model.addAttribute("drivingLicenseForm", drivingLicenseForm);
        model.addAttribute("listChapter", chapterServices.findAllChapter());
        return "create-driving-license";
    }

    @PostMapping(value = { "/create-driving-license" })
    public String createDrivingLicense(@Validated DrivingLicenseForm drivingLicenseForm, BindingResult result, Model model) {
        model.addAttribute("listChapter", chapterServices.findAllChapter());
        if (result.hasErrors()) {
            return "create-driving-license";
        }
        drivingLicenseServices.createDrivingLicense(drivingLicenseForm);
        model.addAttribute(Constant.STATUS_SUCCESS, "Tạo hạng bằng lái thành công!");
        model.addAttribute("drivingLicenseForm", new DrivingLicenseForm());
        return "create-driving-license";
    }
    
    @PostMapping(value = { "/update-driving-license" })
    public String updateDrivingLicense(@Validated DrivingLicenseForm drivingLicenseForm, BindingResult result, Model model) {
        model.addAttribute("listChapter", chapterServices.findAllChapter());
        if (result.hasErrors()) {
            return "update-driving-license";
        }
        drivingLicenseServices.updateDrivingLicense(drivingLicenseForm);
        model.addAttribute(Constant.STATUS_SUCCESS, "Chỉnh sửa hạng bằng lái thành công!");
        model.addAttribute("drivingLicenseForm", new DrivingLicenseForm());
        return "update-driving-license";
    }
    
    @GetMapping(value = { "/update-driving-license" })
    public String updateDrivingLicense(Model model, Long drivingLicenseId) {
    	model.addAttribute("drivingLicenseForm", drivingLicenseServices.getObjectUpdate(drivingLicenseId));
    	return "update-driving-license";
    }
    
    @GetMapping(value = { "/detail-driving-license" })
    public String visitDetailDrivingLicenseManagementPage(Long drivingLicenseId, Model model) {
        model.addAttribute("drivingLicense", drivingLicenseServices.findById(drivingLicenseId));
        return "detail-driving-license";
    }
}
