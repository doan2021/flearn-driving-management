package com.doanfpt.management.application.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.doanfpt.management.application.dto.ChapterForm;
import com.doanfpt.management.application.utils.ValidationApplicationUtils;

@Component
public class ChapterCreateValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == ChapterForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// Kiểm tra các field của chapterForm. 
        ValidationApplicationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.chapterForm.name");
        ValidationApplicationUtils.rejectNameIncorrectFormat(errors, "name", "Pattern.chapterForm.name");
        ValidationApplicationUtils.rejectIfEmptyOrWhitespace(errors, "content", "NotEmpty.chapterForm.content");
        ValidationApplicationUtils.rejectContentIncorrectFormat(errors, "content", "Pattern.chapterForm.content");
	}
}
