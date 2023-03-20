package com.flearndriving.management.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerForm {

    private Long customerId;

    private String userName;

    private String firstName;

    private String middleName;

    private String lastName;

    private String password;

    private String confirmPassword;

    private Integer gender;

    private String email;

    private String birthDay;

    private String numberPhone;

    private Long roleId;

    private String description;

}
