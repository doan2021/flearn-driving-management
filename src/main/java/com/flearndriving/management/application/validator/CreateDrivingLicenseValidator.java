/**
 * 
 */
package com.flearndriving.management.application.validator;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.flearndriving.management.application.common.Common;
import com.flearndriving.management.application.dto.request.DrivingLicenseForm;
import com.flearndriving.management.application.dto.request.NumberOfChapter;

@Component
public class CreateDrivingLicenseValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == DrivingLicenseForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        DrivingLicenseForm drivingLicenseForm = (DrivingLicenseForm) target;

        // Validate required
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", 
                "NotEmpty.drivingLicenseForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", 
                "NotEmpty.drivingLicenseForm.price");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numberQuestion",
                "NotEmpty.drivingLicenseForm.numberQuestion");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numberQuestionCorect",
                "NotEmpty.drivingLicenseForm.numberQuestionCorect");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "examMinutes", 
                "NotEmpty.drivingLicenseForm.examMinutes");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numberYearExpires",
                "NotEmpty.drivingLicenseForm.numberYearExpires");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "listNumberOfChapter",
                "NotEmpty.drivingLicenseForm.listNumberOfChapter");

        // Validate name
        if ((!errors.hasFieldErrors("name")) && Common.isInvalidMaxLengthString(drivingLicenseForm.getName(), 255)) {
            errors.rejectValue("name", "Maxlength.drivingLicenseForm.name");
        }

        // Validate price
        if (!errors.hasFieldErrors("price")) {
            if (!NumberUtils.isCreatable(drivingLicenseForm.getPrice())) {
                errors.rejectValue("price", "WrongNumber.drivingLicenseForm.price");
            } else if (Common.isInvalidMaxLengthString(drivingLicenseForm.getPrice(), 10)) {
                errors.rejectValue("price", "Maxlength.drivingLicenseForm.price");
            }
        }

        // Validate examMinutes
        if (!errors.hasFieldErrors("examMinutes")) {
            if (!StringUtils.isNumeric(drivingLicenseForm.getExamMinutes())) {
                errors.rejectValue("examMinutes", "WrongNumber.drivingLicenseForm.examMinutes");
            } else if (NumberUtils.toInt(drivingLicenseForm.getExamMinutes()) > 720) {
                errors.rejectValue("examMinutes", "Maxlength.drivingLicenseForm.examMinutes");
            }
        }

        // Validate numberQuestion
        if (!errors.hasFieldErrors("numberQuestion")) {
            if (!StringUtils.isNumeric(drivingLicenseForm.getNumberQuestion())) {
                errors.rejectValue("numberQuestion", "WrongNumber.drivingLicenseForm.numberQuestion");
            } else if (Common.isInvalidMaxLengthString(drivingLicenseForm.getNumberQuestion(), 5)) {
                errors.rejectValue("numberQuestion", "Maxlength.drivingLicenseForm.numberQuestion");
            }
        }

        // Validate numberQuestionCorect
        if (!errors.hasFieldErrors("numberQuestionCorect") && !errors.hasFieldErrors("numberQuestion")) {
            if (!StringUtils.isNumeric(drivingLicenseForm.getNumberQuestionCorrect())) {
                errors.rejectValue("numberQuestionCorect", "WrongNumber.drivingLicenseForm.numberQuestionCorect");
            } else if (NumberUtils.toInt(drivingLicenseForm.getNumberQuestionCorrect()) > NumberUtils
                    .toInt(drivingLicenseForm.getNumberQuestion())) {
                errors.rejectValue("numberQuestionCorect", "Maxlength.drivingLicenseForm.numberQuestionCorect");
            }
        }

        // Validate numberQuestionParalysis
        if (!errors.hasFieldErrors("numberQuestionParalysis") && !errors.hasFieldErrors("numberQuestion")) {
            if (!StringUtils.isNumericSpace(drivingLicenseForm.getNumberQuestionParalysis())) {
                errors.rejectValue("numberQuestionParalysis", "WrongNumber.drivingLicenseForm.numberQuestionParalysis");
            } else if (NumberUtils.toInt(drivingLicenseForm.getNumberQuestionParalysis()) > NumberUtils
                    .toInt(drivingLicenseForm.getNumberQuestion())) {
                errors.rejectValue("numberQuestionParalysis", "Maxlength.drivingLicenseForm.numberQuestionParalysis");
            }
        }
        
        // Validate numberYearExpires
        if (!errors.hasFieldErrors("numberYearExpires")) {
            if (!StringUtils.isNumeric(drivingLicenseForm.getNumberYearExpires())) {
                errors.rejectValue("numberYearExpires", "WrongNumber.drivingLicenseForm.numberYearExpires");
            }
        }
        
        // Validate description
        if (!errors.hasFieldErrors("description")) {
            if (Common.isInvalidMaxLengthString(drivingLicenseForm.getDescription(), 4000)) {
                errors.rejectValue("description", "Maxlength.drivingLicenseForm.description");
            }
        }

        // Validate listNumberOfChapter
        if (!errors.hasFieldErrors("listNumberOfChapter") && !errors.hasFieldErrors("numberQuestion") && !errors.hasFieldErrors("numberQuestionParalysis")) {
            if ((numberOfChapterValid(drivingLicenseForm.getListNumberOfChapter()) + NumberUtils.toInt(drivingLicenseForm.getNumberQuestionParalysis())) != NumberUtils
                    .toInt(drivingLicenseForm.getNumberQuestion())) {
                errors.rejectValue("listNumberOfChapter", "WrongNumber.drivingLicenseForm.listNumberOfChapter");
            }
        }
    }
    
    private Integer numberOfChapterValid(List<NumberOfChapter> listNumberOfChapter) {
        if (listNumberOfChapter == null) {
            return 0;
        }
        Integer count = 0;
        for (NumberOfChapter numberOfChapter : listNumberOfChapter) {
            if (numberOfChapter.getChapterId() != null) {
                count += NumberUtils.toInt(numberOfChapter.getNumberQuestionInChapter());
            }
        }
        return count;
    }
}
