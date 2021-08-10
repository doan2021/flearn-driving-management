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
        ValidationApplicationUtils.rejectIfEmptyOrWhitespace(errors, "index", "NotEmpty.chapterForm.index");
        ValidationApplicationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.chapterForm.name");

        // Validate name
        if (!errors.hasFieldErrors("index") && Common.isInvalidMaxLengthString(chapterForm.getIndex(), 36)) {
            errors.rejectValue("index", "Maxlength.chapterForm.index");
        }

        // Validate content
        if (!errors.hasFieldErrors("name") && Common.isInvalidMaxLengthString(chapterForm.getName(), 255)) {
            errors.rejectValue("name", "Maxlength.chapterForm.name");
        }

        // Validate description
        if (Common.isInvalidMaxLengthString(chapterForm.getDescription(), 4000)) {
            errors.rejectValue("description", "Maxlength.chapterForm.description");
        }
    }
}
