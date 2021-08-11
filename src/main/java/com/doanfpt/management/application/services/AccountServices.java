package com.doanfpt.management.application.services;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.doanfpt.management.application.common.Common;
import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.dto.AccountForm;
import com.doanfpt.management.application.dto.AccountPrincipal;
import com.doanfpt.management.application.dto.AccountUpdateForm;
import com.doanfpt.management.application.dto.FormSearchAccount;
import com.doanfpt.management.application.entities.Account;
import com.doanfpt.management.application.entities.Role;
import com.doanfpt.management.application.exception.BusinessException;
import com.doanfpt.management.application.respositories.AccountsRespository;
import com.doanfpt.management.application.respositories.RoleRespository;
import com.doanfpt.management.application.specification.AccountSpecification;
import com.doanfpt.management.application.utils.EncrytedPasswordUtils;

@Service
public class AccountServices {

    @Autowired
    private AccountsRespository accountsRespository;

    @Autowired
    private RoleRespository roleRespository;

    public List<Account> findAllAccount() {
        return accountsRespository.findAll();
    }

    public Account findByEmail(String email) {
        return accountsRespository.findByEmail(email);
    }

    @Transactional
    public void createAccount(AccountForm accountForm) {
        String encrytedPassword = EncrytedPasswordUtils.encrytePassword(accountForm.getPassword());
        Account account = new Account();
        account.setUserName(accountForm.getUserName());
        account.setFirstName(accountForm.getFirstName());
        account.setMiddleName(accountForm.getMiddleName());
        account.setLastName(accountForm.getLastName());
        account.setBirthDay(Common.stringToDate(accountForm.getBirthDay()));
        account.setEmail(accountForm.getEmail());
        account.setNumberPhone(accountForm.getNumberPhone());
        account.setEncrytedPassword(encrytedPassword);
        account.setGender(accountForm.getGender());
        Role role = roleRespository.getOne(accountForm.getRoleId());
        account.setRole(role);
        account.setCreateBy(Common.getUsernameLogin());
        account.setCreateAt(Common.getSystemDate());
        account.setUpdateBy(Common.getUsernameLogin());
        account.setUpdateAt(Common.getSystemDate());
        accountsRespository.save(account);
    }

    public Account getAccountLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AccountPrincipal loginedUser = (AccountPrincipal) auth.getPrincipal();
        return accountsRespository.findByEmail(loginedUser.getEmail());
    }

    public Page<Account> searchAccount(FormSearchAccount formSearchAccount) {
        if (formSearchAccount.getPageNumber() == null) {
            formSearchAccount.setPageNumber(0);
        }
        Specification<Account> conditions = Specification.where(null);
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
        PageRequest pageable = PageRequest.of(formSearchAccount.getPageNumber(), Constant.RECORD_PER_PAGE);
        Page<Account> listAccount = accountsRespository.findAll(conditions, pageable);
        return listAccount;
    }

    @Transactional
    public void deleteAccount(Long accountId) {
        Account account = accountsRespository.findByAccountId(accountId);
        if (account == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Người dùng không tồn tại!");
        }
        accountsRespository.delete(account);
    }

    public Object getObjectUpdate(Long accountId) {
        AccountForm accountForm = new AccountForm();
        Account account = accountsRespository.findByAccountId(accountId);
        accountForm.setAccountId(account.getAccountId());
        accountForm.setFirstName(account.getFirstName());
        accountForm.setMiddleName(account.getMiddleName());
        accountForm.setLastName(account.getLastName());
        accountForm.setUserName(account.getUserName());
        accountForm.setBirthDay(account.getBirthDay() == null ? StringUtils.EMPTY
                : DateFormatUtils.format(account.getBirthDay(), Constant.FORMAT_DATE));
        accountForm.setNumberPhone(account.getNumberPhone());
        accountForm.setEmail(account.getEmail());
        accountForm.setGender(account.getGender());
        accountForm.setDescription(account.getDescription());
        accountForm.setRoleId(account.getRole().getRoleId());
        return accountForm;
    }

    @Transactional
    public void updateAccount(AccountUpdateForm accountUpdateForm) {
        if (accountUpdateForm == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Dữ liệu truyền vào không đúng!");
        }
        Account account = accountsRespository.findByAccountId(accountUpdateForm.getAccountId());
        if (account == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Người dùng không tồn tại!");
        }
        account.setFirstName(accountUpdateForm.getFirstName());
        account.setMiddleName(accountUpdateForm.getMiddleName());
        account.setLastName(accountUpdateForm.getLastName());
        account.setBirthDay(Common.stringToDate(accountUpdateForm.getBirthDay()));
        account.setEmail(accountUpdateForm.getEmail());
        account.setNumberPhone(accountUpdateForm.getNumberPhone());
        account.setGender(accountUpdateForm.getGender());
        account.setUpdateBy(Common.getUsernameLogin());
        account.setUpdateAt(Common.getSystemDate());
        account.setDescription(accountUpdateForm.getDescription());
        accountsRespository.save(account);
    }

    public Object getAccountLoginInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AccountPrincipal loginedUser = (AccountPrincipal) auth.getPrincipal();
        AccountForm accountForm = new AccountForm();
        Account account = accountsRespository.findByEmail(loginedUser.getEmail());
        accountForm.setAccountId(account.getAccountId());
        accountForm.setFirstName(account.getFirstName());
        accountForm.setMiddleName(account.getMiddleName());
        accountForm.setLastName(account.getLastName());
        accountForm.setUserName(account.getUserName());
        accountForm.setBirthDay(DateFormatUtils.format(account.getBirthDay(), Constant.FORMAT_DATE));
        accountForm.setNumberPhone(account.getNumberPhone());
        accountForm.setEmail(account.getEmail());
        accountForm.setGender(account.getGender());
        accountForm.setRoleId(account.getRole().getRoleId());
        return accountForm;
    }

    public Integer countAccount() {
        return accountsRespository.countAccount();
    }

}
