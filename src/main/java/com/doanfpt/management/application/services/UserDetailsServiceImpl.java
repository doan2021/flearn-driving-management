package com.doanfpt.management.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.entities.Account;
import com.doanfpt.management.application.entities.Role;
import com.doanfpt.management.application.model.AccountPrincipal;
import com.doanfpt.management.application.responsitories.AccountsRespository;
import com.doanfpt.management.application.responsitories.RoleRespository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AccountsRespository accountsRespository;
    
    @Autowired
    private RoleRespository roleRespository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Role role = roleRespository.getOne(Constant.ROLE_ID_ADMIN); 
        Account account = accountsRespository.findByUserNameAndIsDeleteAndRole(userName, Constant.IS_NOT_DELETE, role);
        AccountPrincipal userDetails = AccountPrincipal.create(account);
        return userDetails;
    }
}
