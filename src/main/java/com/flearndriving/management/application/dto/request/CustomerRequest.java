package com.flearndriving.management.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerRequest {

    private Long id;

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
