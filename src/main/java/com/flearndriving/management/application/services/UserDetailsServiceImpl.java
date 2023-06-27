package com.flearndriving.management.application.services;

import com.flearndriving.management.application.entities.Customer;
import com.flearndriving.management.application.respositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUserName(userName);
        if (Objects.isNull(customer)) {
            throw new UsernameNotFoundException("Tên đăng nhập không đúng!");
        }
        return new User(customer.getUserName(), customer.getEncrytedPassword(), true, true, true, true,
                Collections.singletonList(new SimpleGrantedAuthority(customer.getRole().getRoleName())));
    }
}
