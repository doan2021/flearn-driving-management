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
        // Form mục tiêu
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
    public String saveChapter(@Validated ChapterForm chapterForm, BindingResult result, Model model) {
    	try {
			if (result.hasErrors()) {
				return "create-chapter";
			}
			boolean createSuccess = chapterServices.saveChapter(chapterForm);
			if (createSuccess) {
				model.addAttribute("messageSuccess", "Tạo chương mới thành công!");
			} else {
				model.addAttribute("messageError", "Quá trình tạo chương thất bại!");
			}
			model.addAttribute("chapterForm", chapterForm);
			return "create-chapter";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "403";
		}
    }
	@PostMapping(value = { "/edit-chapter-detail" })
	public String editChapterDetail(ChapterForm chapterForm,Model model) {
        try {
        	chapterServices.editChapterDetail(chapterForm);
            model.addAttribute("chapterForm", chapterForm);
            return "edit-chapter-detail";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "403";
        }
	}
	
	@GetMapping(value = { "/edit-chapter-detail" })
	public String editPageChapterDetail(Long chapterId ,Model model) {
		try {
			model.addAttribute("chapterForm", chapterServices.getObjectUpdate(chapterId));
			return "edit-chapter-detail";
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
		}
		return "403";
	}
}
