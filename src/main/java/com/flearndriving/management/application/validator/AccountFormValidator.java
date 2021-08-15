/**
 * 
 */
package com.flearndriving.management.application.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.flearndriving.management.application.common.Common;
import com.flearndriving.management.application.dto.AccountForm;
import com.flearndriving.management.application.respositories.AccountsRespository;

/**
 * @author tamdu
 *
 */
@Component
public class AccountFormValidator implements Validator {

    @Autowired
    private AccountsRespository accountsRespository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == AccountForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountForm accountForm = (AccountForm) target;
        // Kiểm tra các field của accountForm        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.accountForm.lastName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.accountForm.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDay", "NotEmpty.accountForm.birthDay");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numberPhone", "NotEmpty.accountForm.numberPhone");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.accountForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.accountForm.userName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.accountForm.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.accountForm.confirmPassword");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.accountForm.gender");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleId", "NotEmpty.accountForm.roleId");
        
        if (!errors.hasErrors()) {
            // Duplicate email đã được sử dụng bởi tài khoản khác.
            if (accountsRespository.findByEmail(accountForm.getEmail()) != null) {
                errors.rejectValue("email", "Duplicate.accountForm.email");
            }
            
            // Tên tài khoản đã bị sử dụng bởi người khác.
            if (accountsRespository.existsByUserName(accountForm.getUserName())) {
                errors.rejectValue("userName", "Duplicate.accountForm.userName");
            }
            
            if (accountForm.getPassword().length() < 8) {
                errors.rejectValue("password", "Pattern.accountForm.password");
            }
            
            if (!accountForm.getConfirmPassword().equals(accountForm.getPassword())) {
                errors.rejectValue("confirmPassword", "Match.accountForm.confirmPassword");
            }
        }
        
        // Validate maxlength for lastName, middleName, firstName            
        if (!errors.hasFieldErrors("lastName") && Common.isInvalidMaxLengthString(accountForm.getLastName(), 36)) {
        	errors.rejectValue("lastName", "Maxlength.accountForm.lastName");
        }
        
        if (!errors.hasFieldErrors("middleName") && Common.isInvalidMaxLengthString(accountForm.getMiddleName(), 36)) {
        	errors.rejectValue("middleName", "Maxlength.accountForm.middleName");
        }
        
        if (!errors.hasFieldErrors("firstName") && Common.isInvalidMaxLengthString(accountForm.getFirstName(), 36)) {
        	errors.rejectValue("firstName", "Maxlength.accountForm.firstName");
        }
        
        // Validate maxlength for birthday, numberPhone
        if (!errors.hasFieldErrors("birthDay") && Common.isInvalidMaxLengthString(accountForm.getBirthDay(), 10)) {
        	errors.rejectValue("birthDay", "Maxlength.accountForm.birthDay");
        }
        
        if (!errors.hasFieldErrors("numberPhone") && Common.isInvalidMaxLengthString(accountForm.getNumberPhone(), 10)) {
        	errors.rejectValue("numberPhone", "Maxlength.accountForm.numberPhone");
        }
        
        // Validate maxlength for email, username, password
        if (!errors.hasFieldErrors("email") && Common.isInvalidMaxLengthString(accountForm.getEmail(), 255)) {
        	errors.rejectValue("email", "Maxlength.accountForm.email");
        }
     
        if (!errors.hasFieldErrors("userName") && Common.isInvalidMaxLengthString(accountForm.getUserName(), 36)) {
        	errors.rejectValue("userName", "Maxlength.accountForm.userName");
        }
        
        if (!errors.hasFieldErrors("password") && Common.isInvalidMaxLengthString(accountForm.getPassword(), 36)) {
        	errors.rejectValue("password", "Maxlength.accountForm.password");
        }
    }
}
