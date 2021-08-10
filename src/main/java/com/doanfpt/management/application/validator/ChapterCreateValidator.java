package com.doanfpt.management.application.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.doanfpt.management.application.common.Common;
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
        ChapterForm chapterForm = (ChapterForm) target;
        // Kiểm tra các field của chapterForm.
        ValidationApplicationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.chapterForm.name");
        ValidationApplicationUtils.rejectIfEmptyOrWhitespace(errors, "content", "NotEmpty.chapterForm.content");

        // Validate name
        if (!errors.hasFieldErrors("name") && Common.isInvalidMaxLengthString(chapterForm.getName(), 36)) {
            errors.rejectValue("name", "Maxlength.chapterForm.name");
        }

        // Validate content
        if (!errors.hasFieldErrors("content") && Common.isInvalidMaxLengthString(chapterForm.getContent(), 255)) {
            errors.rejectValue("content", "Maxlength.chapterForm.content");
        }

        // Validate description
        if (Common.isInvalidMaxLengthString(chapterForm.getDescription(), 4000)) {
            errors.rejectValue("description", "Maxlength.chapterForm.description");
        }
    }
}
