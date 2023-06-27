package com.flearndriving.management.application.converter;

import com.flearndriving.management.application.common.Common;
import com.flearndriving.management.application.dto.request.CustomerRequest;
import com.flearndriving.management.application.entities.Customer;
import com.flearndriving.management.application.entities.Role;
import com.flearndriving.management.application.respositories.RoleRepository;
import com.flearndriving.management.application.utils.EncrytedPasswordUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class CustomerConverter {

    @Resource
    private RoleRepository roleRepository;

    public Customer buildCustomer(CustomerRequest source) {
        Role role = roleRepository.getOne(source.getRoleId());
        return Customer.builder()
                .userName(source.getUserName())
                .firstName(source.getFirstName())
                .middleName(source.getMiddleName())
                .lastName(source.getLastName())
                .birthDay(Common.stringToDate(source.getBirthDay()))
                .email(source.getEmail())
                .numberPhone(source.getNumberPhone())
                .password(EncrytedPasswordUtils.encrytePassword(source.getPassword()))
                .gender(source.getGender())
                .role(role)
                .build();
    }
}
