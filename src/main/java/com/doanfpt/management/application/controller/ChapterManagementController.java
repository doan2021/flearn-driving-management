package com.doanfpt.management.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.dto.ChapterForm;
import com.doanfpt.management.application.dto.FormSearchChapter;
import com.doanfpt.management.application.services.ChapterServices;
import com.doanfpt.management.application.services.QuestionServices;

@Controller
@RequestMapping("/management")
public class ChapterManagementController {
    
    @Autowired
    ChapterServices chapterServices;
    
    @Autowired
    QuestionServices questionServices;

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
    public String visitChapterDetailPage(Long chapterId, Integer pageNumber, Model model) {
        model.addAttribute("chapter", chapterServices.getChapterDetail(chapterId));
        model.addAttribute(Constant.PAGE_CONTENT_NAME, questionServices.getQuestionInChapter(chapterId, pageNumber));
        model.addAttribute("isSearch", false);
        return "chapter-detail";
    }
    
    @GetMapping(value = { "/create-chapter" })
    public String createChapter(Model model) {
    	ChapterForm chapterForm = new ChapterForm();
        model.addAttribute("chapterForm", chapterForm);
        return "create-chapter";
    }
    
    @PostMapping(value = { "/save-chapter" })
    public String saveChapter(@ModelAttribute("chapterForm") ChapterForm chapterForm) {
    	chapterServices.saveChapter(chapterForm);
        return "create-chapter";
    }
}
