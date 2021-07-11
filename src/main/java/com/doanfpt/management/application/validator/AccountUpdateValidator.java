/**
 * 
 */
package com.doanfpt.management.application.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.doanfpt.management.application.dto.AccountUpdateForm;
import com.doanfpt.management.application.utils.ValidationApplicationUtils;

/**
 * @author tamdu
 *
 */
@Component
public class AccountUpdateValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == AccountUpdateForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        // Kiểm tra các field của accountForm. 
        ValidationApplicationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.accountForm.firstName");
        ValidationApplicationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.accountForm.lastName");
        ValidationApplicationUtils.rejectIfEmptyOrWhitespace(errors, "numberPhone", "NotEmpty.accountForm.numberPhone");
        ValidationApplicationUtils.rejectPhoneNumberIncorrectFormat(errors, "numberPhone", "Pattern.accountForm.numberPhone");
        ValidationApplicationUtils.rejectIfEmptyOrWhitespace(errors, "birthDay", "NotEmpty.accountForm.birthDay");
        ValidationApplicationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.accountForm.email");
        ValidationApplicationUtils.rejectEmailIncorrectFormat(errors, "email", "Pattern.accountForm.email");
    }
}
