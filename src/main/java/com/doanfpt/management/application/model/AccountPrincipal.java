package com.doanfpt.management.application.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.entities.Account;

public class AccountPrincipal implements OAuth2User, UserDetails {

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
    private Map<String, Object> attributes;

    public AccountPrincipal(String email, String fullName, String password, String userName,
            Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.authorities = authorities;
    }

    public AccountPrincipal(String fullName, String email, Map<String, Object> attributes, Collection<? extends GrantedAuthority> authorities) {
        this.fullName = fullName;
        this.email = email;
        this.attributes = attributes;
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

    public static AccountPrincipal create(Account account, Map<String, Object> attributes) {
        AccountPrincipal accountPrincipal = AccountPrincipal.create(account);
        accountPrincipal.setAttributes(attributes);
        return accountPrincipal;
    }
    
    public static AccountPrincipal create(Map<String, Object> attributes) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(Constant.ROLE_USER));
        AccountPrincipal accountPrincipal = new AccountPrincipal((String)attributes.get("name"), (String)attributes.get("email"), attributes, authorities);
        return accountPrincipal;
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

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return String.valueOf(this.id);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
