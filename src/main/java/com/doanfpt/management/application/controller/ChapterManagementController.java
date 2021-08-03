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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.dto.ChapterForm;
import com.doanfpt.management.application.dto.FormSearchChapter;
import com.doanfpt.management.application.services.ChapterServices;
import com.doanfpt.management.application.services.QuestionServices;
import com.doanfpt.management.application.validator.ChapterCreateValidator;

@Controller
@RequestMapping("/management")
public class ChapterManagementController {

    @Autowired
    ChapterServices chapterServices;

    @Autowired
    QuestionServices questionServices;

    @Autowired
    ChapterCreateValidator chapterCreateValidator;

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        if (target.getClass() == ChapterForm.class) {
            dataBinder.setValidator(chapterCreateValidator);
        }
    }

    @GetMapping(value = { "/chapter" })
    public String visitChapterPage(FormSearchChapter formSearchChapter, Model model) {
        model.addAttribute(Constant.PAGE_CONTENT_NAME, chapterServices.searchChapter(formSearchChapter));
        model.addAttribute("formSearchChapter", new FormSearchChapter());
        return "chapter-management";
    }

    @PostMapping(value = { "/search-chapter" })
    public String searchChapter(FormSearchChapter formSearchChapter, Model model) {
        model.addAttribute(Constant.PAGE_CONTENT_NAME, chapterServices.searchChapter(formSearchChapter));
        model.addAttribute("formSearchChapter", formSearchChapter);
        return "chapter-management";
    }

    @GetMapping(value = { "/chapter-detail" })
    public String visitChapterDetailPage(Long chapterId, Model model) {
        model.addAttribute("chapter", chapterServices.getChapterDetail(chapterId));
        return "chapter-detail";
    }

    @GetMapping(value = { "/create-chapter" })
    public String createChapter(Model model) {
        ChapterForm chapterForm = new ChapterForm();
        model.addAttribute("chapterForm", chapterForm);
        return "create-chapter";
    }

    @PostMapping(value = { "/save-chapter" })
    public String saveChapter(@Validated ChapterForm chapterForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "create-chapter";
        }
        chapterServices.saveChapter(chapterForm);
        model.addAttribute(Constant.STATUS_SUCCESS, "Tạo chương mới thành công!");
        model.addAttribute("chapterForm", new ChapterForm());
        return "create-chapter";
    }

    @PostMapping(value = { "/update-chapter" })
    public String editChapterDetail(@Validated ChapterForm chapterForm, BindingResult result, Model model) {
    	if (result.hasErrors()) {
            return "update-chapter";
        }
    	chapterServices.editChapterDetail(chapterForm);
        model.addAttribute(Constant.STATUS_SUCCESS, "Tạo chương mới thành công!");
        model.addAttribute("chapterForm", chapterServices.getObjectUpdate(chapterForm.getChapterId()));
        return "update-chapter";
    }

    @GetMapping(value = { "/update-chapter" })
    public String editPageChapterDetail(Long chapterId, Model model) {
        model.addAttribute("chapterForm", chapterServices.getObjectUpdate(chapterId));
        return "update-chapter";
    }
    
    @PostMapping(value = {"/delete-chapter"})
    public String deleteChapter(Long chapterId, RedirectAttributes redirAttrs) {
    	chapterServices.deleteChapter(chapterId);
        redirAttrs.addFlashAttribute(Constant.STATUS_SUCCESS, "Xóa chương thành công!");
        return "redirect:chapter";
    }
}
