package com.flearndriving.management.application.utils;

import com.flearndriving.management.application.services.CommonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Autowired
    CommonServices commonServices;

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable(commonServices.getUsernameLogin());
    }
}
