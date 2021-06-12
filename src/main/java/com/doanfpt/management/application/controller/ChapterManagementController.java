package com.doanfpt.management.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.dto.FormSearchChapter;
import com.doanfpt.management.application.entities.Chapter;
import com.doanfpt.management.application.services.ChapterServices;

@Controller
@RequestMapping("/management")
public class ChapterManagementController {
    
    @Autowired
    ChapterServices chapterServices;

    @GetMapping(value = { "/chapter" })
    public String visitChapterPage(Integer pageNumber, Model model) {
        model.addAttribute(Constant.PAGE_CONTENT_NAME, chapterServices.getAllChapter(pageNumber));
        model.addAttribute("formSearchChapter", new FormSearchChapter());
        model.addAttribute("isSearch", false);
        return "chapter-management";
    }
    
    @PostMapping(value = { "/search-chapter" })
    public String searchChapter(FormSearchChapter formSearchChapter, Model model) {
        model.addAttribute(Constant.PAGE_CONTENT_NAME, chapterServices.searchChapter(formSearchChapter));
        model.addAttribute("formSearchChapter", formSearchChapter);
        model.addAttribute("isSearch", true);
        return "chapter-management";
    }
    
    @GetMapping(value = { "/create-chapter" })
    public String createChapter(Model model) {
        model.addAttribute("chapterForm", new Chapter());
        return "create-chapter";
    }
    
    @PostMapping(value = { "/save-chapter" })
    public String saveChapter(@ModelAttribute("chapterForm") Chapter chapterForm, Model model) {
        chapterServices.saveChapter(chapterForm);
        return "create-chapter";
    }
}
