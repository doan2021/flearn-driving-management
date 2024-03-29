package com.flearndriving.management.application.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.flearndriving.management.application.common.Common;
import com.flearndriving.management.application.dto.request.ExamQuestionsForm;
import com.flearndriving.management.application.entities.DrivingLicense;
import com.flearndriving.management.application.entities.ExamStructure;
import com.flearndriving.management.application.entities.Question;
import com.flearndriving.management.application.respositories.DrivingLicenseRepository;

@Component
public class CreateExamQuestionValidator implements Validator {

    @Autowired
    DrivingLicenseRepository drivingLicenseRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == ExamQuestionsForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ExamQuestionsForm examQuestionsForm = (ExamQuestionsForm) target;
        // Validate required
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.examQuestionsForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "listIdQuestion",
                "NotEmpty.examQuestionsForm.listIdQuestion");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "listIdQuestionParalysis",
                "NotEmpty.examQuestionsForm.listIdQuestionParalysis");

        // Validate name
        if ((!errors.hasFieldErrors("name")) && Common.isInvalidMaxLengthString(examQuestionsForm.getName(), 255)) {
            errors.rejectValue("name", "Maxlength.examQuestionsForm.name");
        }

        // Validate description
        if (!errors.hasFieldErrors("description")) {
            if (Common.isInvalidMaxLengthString(examQuestionsForm.getDescription(), 4000)) {
                errors.rejectValue("description", "Maxlength.examQuestionsForm.description");
            }
        }

        if (!errors.hasFieldErrors("drivingLicenseId")) {
            DrivingLicense drivingLicense = drivingLicenseRepository.findByDrivingLicenseId(examQuestionsForm.getDrivingLicenseId());
            if (drivingLicense == null) {
                errors.rejectValue("drivingLicenseId", "Notfound.examQuestionsForm.drivingLicenseId");
                return;
            }

            // Validate listIdQuestionParalysis
            if (!errors.hasFieldErrors("listIdQuestionParalysis")) {
                if (examQuestionsForm.getListIdQuestionParalysis().size() != drivingLicense
                        .getNumberQuestionParalysis()) {
                    errors.rejectValue("listIdQuestionParalysis",
                            "ErrorNumberQuestion.examQuestionsForm.listIdQuestionParalysis");
                }
            }

            // Validate listIdQuestion
            if (!errors.hasFieldErrors("listIdQuestion")) {
                if (!isValidNumberQuestionForChapter(drivingLicense.getListExamStructure(),
                        examQuestionsForm.getListIdQuestion())) {
                    errors.rejectValue("listIdQuestion", "ErrorNumberQuestion.examQuestionsForm.listIdQuestion");
                }
                
            }
        }
    }

    public boolean isValidNumberQuestionForChapter(List<ExamStructure> listExamStructure, List<Long> listIdQuestion) {
        if (listExamStructure == null) {
            return false;
        }
        for (ExamStructure examStructure : listExamStructure) {
            List<Question> listQuestion = examStructure.getChapter().getListQuestion();
            int count = 0;
            for (Question question : listQuestion) {
                if (listIdQuestion.contains(question.getId())) {
                    count++;
                }
            }
            if (count != examStructure.getNumberQuestion()) {
                return false;
            }
        }
        return true;
    }
}
