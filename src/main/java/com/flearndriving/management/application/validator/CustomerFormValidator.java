/**
 *
 */
package com.flearndriving.management.application.validator;

import com.flearndriving.management.application.common.Common;
import com.flearndriving.management.application.dto.request.CustomerRequest;
import com.flearndriving.management.application.respositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CustomerFormValidator implements Validator {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == CustomerRequest.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerRequest customerForm = (CustomerRequest) target;
        // Kiểm tra các field của customerForm
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.customerForm.lastName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.customerForm.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "birthDay", "NotEmpty.customerForm.birthDay");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numberPhone", "NotEmpty.customerForm.numberPhone");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.customerForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "NotEmpty.customerForm.userName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.customerForm.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.customerForm.confirmPassword");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "NotEmpty.customerForm.gender");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleId", "NotEmpty.customerForm.roleId");

        if (!errors.hasErrors()) {
            // Duplicate email đã được sử dụng bởi tài khoản khác.
            if (customerRepository.findByEmail(customerForm.getEmail()) != null) {
                errors.rejectValue("email", "Duplicate.customerForm.email");
            }

            // Tên tài khoản đã bị sử dụng bởi người khác.
            if (customerRepository.countByUserName(customerForm.getUserName()) > 0) {
                errors.rejectValue("userName", "Duplicate.customerForm.userName");
            }

            if (customerForm.getPassword().length() < 8) {
                errors.rejectValue("password", "Pattern.customerForm.password");
            }

            if (!customerForm.getConfirmPassword().equals(customerForm.getPassword())) {
                errors.rejectValue("confirmPassword", "Match.customerForm.confirmPassword");
            }
        }

        // Validate maxlength for lastName, middleName, firstName            
        if (!errors.hasFieldErrors("lastName") && Common.isInvalidMaxLengthString(customerForm.getLastName(), 36)) {
            errors.rejectValue("lastName", "Maxlength.customerForm.lastName");
        }

        if (!errors.hasFieldErrors("middleName") && Common.isInvalidMaxLengthString(customerForm.getMiddleName(), 36)) {
            errors.rejectValue("middleName", "Maxlength.customerForm.middleName");
        }

        if (!errors.hasFieldErrors("firstName") && Common.isInvalidMaxLengthString(customerForm.getFirstName(), 36)) {
            errors.rejectValue("firstName", "Maxlength.customerForm.firstName");
        }

        // Validate maxlength for birthday, numberPhone
        if (!errors.hasFieldErrors("birthDay") && Common.isInvalidMaxLengthString(customerForm.getBirthDay(), 10)) {
            errors.rejectValue("birthDay", "Maxlength.customerForm.birthDay");
        }

        if (!errors.hasFieldErrors("numberPhone") && Common.isInvalidMaxLengthString(customerForm.getNumberPhone(), 10)) {
            errors.rejectValue("numberPhone", "Maxlength.customerForm.numberPhone");
        }

        // Validate maxlength for email, username, password
        if (!errors.hasFieldErrors("email") && Common.isInvalidMaxLengthString(customerForm.getEmail(), 255)) {
            errors.rejectValue("email", "Maxlength.customerForm.email");
        }

        if (!errors.hasFieldErrors("userName") && Common.isInvalidMaxLengthString(customerForm.getUserName(), 36)) {
            errors.rejectValue("userName", "Maxlength.customerForm.userName");
        }

        if (!errors.hasFieldErrors("password") && Common.isInvalidMaxLengthString(customerForm.getPassword(), 36)) {
            errors.rejectValue("password", "Maxlength.customerForm.password");
        }
    }
}
