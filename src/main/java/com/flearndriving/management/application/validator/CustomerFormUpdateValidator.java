/**
 * 
 */
package com.flearndriving.management.application.validator;

import com.flearndriving.management.application.dto.request.CustomerRequest;
import com.flearndriving.management.application.respositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CustomerFormUpdateValidator implements Validator {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == CustomerRequest.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerRequest appUserForm = (CustomerRequest) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.customerForm.userName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.customerForm.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.customerForm.lastName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.customerForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.customerForm.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.customerForm.confirmPassword");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.customerForm.gender");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleId", "NotEmpty.customerForm.roleId");
        if (!errors.hasErrors()) {
            if (customerRepository.findByEmail(appUserForm.getEmail()) != null) {
                // Duplicate email đã được sử dụng bởi tài khoản khác.
                errors.rejectValue("email", "Duplicate.customerForm.email");
            }

            if (customerRepository.countByUserName(appUserForm.getUserName()) > 0) {
                // Tên tài khoản đã bị sử dụng bởi người khác.
                errors.rejectValue("userName", "Duplicate.customerForm.userName");
            }
            if (appUserForm.getPassword().length() < 8) {
                errors.rejectValue("password", "Pattern.customerForm.password");
            }
            if (!appUserForm.getConfirmPassword().equals(appUserForm.getPassword())) {
                errors.rejectValue("confirmPassword", "Match.customerForm.confirmPassword");
            }
        }
    }
}
