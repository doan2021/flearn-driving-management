package com.flearndriving.management.application.services.impl;

import com.flearndriving.management.application.services.CommonServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommonServicesImpl implements CommonServices {

    @Override
    public String getUsernameLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
