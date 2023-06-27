package com.flearndriving.management.application.validator;

import com.flearndriving.management.application.common.Common;
import com.flearndriving.management.application.dto.request.ChapterRequest;
import com.flearndriving.management.application.utils.ValidationApplicationUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ChapterCreateValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == ChapterRequest.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ChapterRequest chapterForm = (ChapterRequest) target;
        // Kiểm tra các field của chapterForm.
        ValidationApplicationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.chapterForm.index");
        ValidationApplicationUtils.rejectIfEmptyOrWhitespace(errors, "content", "NotEmpty.chapterForm.name");

        // Validate name
        if (!errors.hasFieldErrors("name") && Common.isInvalidMaxLengthString(chapterForm.getName(), 36)) {
            errors.rejectValue("name", "Maxlength.chapterForm.index");
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
