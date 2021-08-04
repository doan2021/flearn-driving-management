/**
 * 
 */
package com.doanfpt.management.application.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.doanfpt.management.application.dto.AccountForm;
import com.doanfpt.management.application.respositories.AccountsRespository;

/**
 * @author tamdu
 *
 */
@Component
public class AccountFormUpdateValidator implements Validator {

    @Autowired
    private AccountsRespository accountsRespository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == AccountForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountForm appUserForm = (AccountForm) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.accountForm.userName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.accountForm.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.accountForm.lastName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.accountForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.accountForm.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.accountForm.confirmPassword");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.accountForm.gender");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleId", "NotEmpty.accountForm.roleId");
        if (!errors.hasErrors()) {
            if (accountsRespository.findByEmail(appUserForm.getEmail()) != null) {
                // Duplicate email đã được sử dụng bởi tài khoản khác.
                errors.rejectValue("email", "Duplicate.accountForm.email");
            }
            if (accountsRespository.existsByUserName(appUserForm.getUserName())) {
                // Tên tài khoản đã bị sử dụng bởi người khác.
                errors.rejectValue("userName", "Duplicate.accountForm.userName");
            }
            if (appUserForm.getPassword().length() < 8) {
                errors.rejectValue("password", "Pattern.accountForm.password");
            }
            if (!appUserForm.getConfirmPassword().equals(appUserForm.getPassword())) {
                errors.rejectValue("confirmPassword", "Match.accountForm.confirmPassword");
            }
        }
    }
}
