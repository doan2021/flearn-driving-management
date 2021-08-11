package com.doanfpt.management.application.dto;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.doanfpt.management.application.entities.Account;

public class AccountPrincipal implements UserDetails {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Long id;
    private String fullName;
    private String userName;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public AccountPrincipal(String email, String fullName, String password, String userName,
            Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.authorities = authorities;
    }

    public static AccountPrincipal create(Account account) {
        if (account == null) {
            throw new UsernameNotFoundException("Account was not found in the database");
        }
        String roleName = account.getRole().getRoleName();
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(roleName));
        String fullName = account.getFirstName().concat(" ").concat(account.getLastName());
        return new AccountPrincipal(account.getEmail(), fullName, account.getEncrytedPassword(), account.getUserName(),
                authorities);
    }

    public Long getId() {
        return this.id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
