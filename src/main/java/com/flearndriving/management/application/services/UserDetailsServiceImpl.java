package com.flearndriving.management.application.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.flearndriving.management.application.entities.Account;
import com.flearndriving.management.application.respositories.AccountsRespository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountsRespository accountsRespository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Account account = accountsRespository.findByUserNameAdmin(userName);
        if (account == null) {
            throw new UsernameNotFoundException("Tên đăng nhập hoặc mật khẩu không đúng!");
        }
        String roleName = account.getRole().getRoleName();
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(roleName));
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        UserDetails userDetails = (UserDetails) new User(account.getUserName(), account.getEncrytedPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        return userDetails;
    }
}
