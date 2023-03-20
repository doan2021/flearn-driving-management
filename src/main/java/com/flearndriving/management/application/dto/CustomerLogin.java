package com.flearndriving.management.application.dto;

import lombok.*;

@Data
public class CustomerLogin {

    private Long customerId;

    private String userName;

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private String description;

    private String urlAvatar;

}
