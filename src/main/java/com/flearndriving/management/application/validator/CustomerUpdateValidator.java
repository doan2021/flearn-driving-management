/**
 * 
 */
package com.flearndriving.management.application.validator;

import com.flearndriving.management.application.dto.CustomerUpdateForm;
import com.flearndriving.management.application.utils.ValidationApplicationUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CustomerUpdateValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == CustomerUpdateForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        // Kiểm tra các field của customerForm.
        ValidationApplicationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.customerForm.firstName");
        ValidationApplicationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.customerForm.lastName");
        ValidationApplicationUtils.rejectIfEmptyOrWhitespace(errors, "numberPhone", "NotEmpty.customerForm.numberPhone");
        ValidationApplicationUtils.rejectPhoneNumberIncorrectFormat(errors, "numberPhone", "Pattern.customerForm.numberPhone");
        ValidationApplicationUtils.rejectIfEmptyOrWhitespace(errors, "birthDay", "NotEmpty.customerForm.birthDay");
        ValidationApplicationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.customerForm.email");
        ValidationApplicationUtils.rejectEmailIncorrectFormat(errors, "email", "Pattern.customerForm.email");
        ValidationApplicationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.customerForm.gender");
    }
}
