package com.flearndriving.management.application.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.flearndriving.management.application.common.Common;
import com.flearndriving.management.application.dto.ExamForm;

@Component
public class ExamCreateValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == ExamForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		ExamForm examForm = (ExamForm) target;
		// Kiểm tra các field của ExamForm.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.examForm.name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateRegisExamEnd", "NotEmpty.examForm.dateRegisExamEnd");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "drivingLicenseId", "NotEmpty.examForm.drivingLicenseId");

		// Validate name
        if (!errors.hasFieldErrors("name") && Common.isInvalidMaxLengthString(examForm.getName(), 255)) {
        	errors.rejectValue("name", "Maxlength.examForm.name");
        }

		// Validate description
        if (!errors.hasFieldErrors("description") && Common.isInvalidMaxLengthString(examForm.getDescription(), 4000)) {
        	errors.rejectValue("description", "Maxlength.examForm.description");
        }
        
	}
}
