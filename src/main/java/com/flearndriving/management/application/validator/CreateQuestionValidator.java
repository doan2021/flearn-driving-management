/**
 * 
 */
package com.flearndriving.management.application.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.flearndriving.management.application.dto.AnswerForm;
import com.flearndriving.management.application.dto.QuestionForm;
import com.flearndriving.management.application.respositories.QuestionsRespository;

/**
 * @author tamdu
 *
 */
@Component
public class CreateQuestionValidator implements Validator {

    @Autowired
    private QuestionsRespository questionsRespository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == QuestionForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        QuestionForm questionForm = (QuestionForm) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "number", "NotEmpty.questionForm.number");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "NotEmpty.questionForm.content");

        if (!errors.hasErrors()) {
            if (questionsRespository.existsByNumber(questionForm.getNumber())) {
                errors.rejectValue("number", "Duplicate.questionForm.number");
            }

            if (questionForm.getListAnswers().size() < 2) {
                errors.rejectValue("listAnswers", "NotEnough.questionForm.listAnswers");
            } else {
                Integer numberTrue = 0;
                for (AnswerForm answerForm : questionForm.getListAnswers()) {
                    if (answerForm.isTrue()) {
                        numberTrue++;
                    }
                }
                if (numberTrue == 0) {
                    errors.rejectValue("listAnswers", "NotEnough.questionForm.listAnswers.isTrue");
                }
            }
        }
    }
}
