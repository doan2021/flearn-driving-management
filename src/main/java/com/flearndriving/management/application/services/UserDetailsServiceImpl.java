package com.flearndriving.management.application.services;

import com.flearndriving.management.application.entities.Customer;
import com.flearndriving.management.application.respositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUserNameAdmin(userName);
        if (customer == null) {
            throw new UsernameNotFoundException("Tên đăng nhập hoặc mật khẩu không đúng!");
        }
        String roleName = customer.getRole().getRoleName();
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(roleName));
        boolean enabled = true;
        boolean customerNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean customerNonLocked = true;
        UserDetails userDetails = (UserDetails) new User(customer.getUserName(), customer.getEncrytedPassword(), enabled, customerNonExpired, credentialsNonExpired, customerNonLocked, authorities);
        return userDetails;
    }
}
