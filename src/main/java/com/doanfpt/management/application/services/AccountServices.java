package com.doanfpt.management.application.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.doanfpt.management.application.common.Common;
import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.dto.AccountForm;
import com.doanfpt.management.application.dto.FormSearchAccount;
import com.doanfpt.management.application.entities.Account;
import com.doanfpt.management.application.entities.Role;
import com.doanfpt.management.application.model.AccountPrincipal;
import com.doanfpt.management.application.responsitories.AccountsRespository;
import com.doanfpt.management.application.responsitories.RoleRespository;
import com.doanfpt.management.application.specification.AccountSpecification;
import com.doanfpt.management.application.utils.EncrytedPasswordUtils;

@Service
public class AccountServices {

    @Autowired
    private AccountsRespository accountsRespository;
    
    @Autowired
    private RoleRespository roleRespository;

    public List<Account> findAllAccount() {
    	Specification<Account> conditions = Specification.where(AccountSpecification.isDelete(false));
        List<Account> listAccount = accountsRespository.findAll(conditions);
        if (listAccount != null) {
            return listAccount;
        }
        return new ArrayList<Account>();
    }
    
    public Account findByEmail(String email) {
        return accountsRespository.findByEmail(email);
    }
    
    @Transactional
    public void createAccount(AccountForm appUserForm) {
        String encrytedPassword = EncrytedPasswordUtils.encrytePassword(appUserForm.getPassword());
        Account account = new Account();
        account.setUserName(appUserForm.getUserName());
        account.setFirstName(appUserForm.getFirstName());
        account.setLastName(appUserForm.getLastName());
        account.setEmail(appUserForm.getEmail());
        account.setGender(appUserForm.getGender());
        account.setEncrytedPassword(encrytedPassword);
        Role role = roleRespository.getOne(Constant.ROLE_ID_ADMIN);
        account.setRole(role);
        accountsRespository.save(account);
    }
    
    public Account getAccountLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AccountPrincipal loginedUser = (AccountPrincipal) auth.getPrincipal();
        return accountsRespository.findByEmail(loginedUser.getEmail());
    }
    
    public List<Account> searchAccount(FormSearchAccount formSearchAccount){
    	
    	Specification<Account> conditions = Specification.where(AccountSpecification.isDelete(false));
    	if (formSearchAccount != null) {
            if (StringUtils.isNotBlank(formSearchAccount.getEmail())) {
                conditions = conditions.and(AccountSpecification.hasEmail(formSearchAccount.getEmail()));
            }
            if (StringUtils.isNotBlank(formSearchAccount.getUserName())) {
                conditions = conditions.and(AccountSpecification.hasUserName(formSearchAccount.getUserName()));
            }

            if (StringUtils.isNotBlank(formSearchAccount.getFullName())) {
                conditions = conditions.and(AccountSpecification.likeFullName(formSearchAccount.getFullName()));
            }
            
            if (formSearchAccount.getGender() != null) {
                conditions = conditions.and(AccountSpecification.hasGender(formSearchAccount.getGender()));
            }
        }
		List<Account> listAccount = accountsRespository.findAll(conditions);
		
    	return listAccount;
    }
    
    @Transactional
    public void deleteAccount(Long accountId) {
    	Account account = accountsRespository.getOne(accountId);
    	account.setDelete(true);
    	account.setUpdateBy(Common.getUsernameLogin());
    	account.setUpdateAt(Common.getSystemDate());
        accountsRespository.save(account);
    }

}
