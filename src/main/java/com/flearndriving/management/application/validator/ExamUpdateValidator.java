package com.flearndriving.management.application.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.flearndriving.management.application.common.Common;
import com.flearndriving.management.application.dto.request.ExamUpdateForm;

@Component
public class ExamUpdateValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == ExamUpdateForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		ExamUpdateForm examUpdateForm = (ExamUpdateForm) target;
		// Kiểm tra các field của ExamUpdateForm.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.examUpdateForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateRegisExamEnd", "NotEmpty.examUpdateForm.dateRegisExamEnd");
		// Validate name
        if (!errors.hasFieldErrors("name") && Common.isInvalidMaxLengthString(examUpdateForm.getName(), 255)) {
        	errors.rejectValue("name", "Maxlength.examUpdateForm.name");
        }

		// Validate description
        if (!errors.hasFieldErrors("description") && Common.isInvalidMaxLengthString(examUpdateForm.getDescription(), 4000)) {
        	errors.rejectValue("description", "Maxlength.examUpdateForm.description");
        }
        
	}
}
