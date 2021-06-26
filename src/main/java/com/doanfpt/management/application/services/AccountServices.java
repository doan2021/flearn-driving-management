package com.doanfpt.management.application.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.doanfpt.management.application.common.Common;
import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.dto.AccountForm;
import com.doanfpt.management.application.dto.FormSearchAccount;
import com.doanfpt.management.application.entities.Account;
import com.doanfpt.management.application.entities.Image;
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
    
    @Autowired
    private Environment env;

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
        account.setListImages(handleImage(account, accountForm.getImage()));
        account.setCreateBy(Common.getUsernameLogin());
        account.setCreateAt(Common.getSystemDate());
        account.setUpdateBy(Common.getUsernameLogin());
        account.setUpdateAt(Common.getSystemDate());
        accountsRespository.save(account);
    }
    
    public List<Image> handleImage(Account account, MultipartFile data) {
        String pathClassPath = env.getProperty("url-class-path");
        String pathFolderUpload = env.getProperty("url-upload-folder-account");
        List<Image> images = new ArrayList<>();
        if (!data.isEmpty()) {
            Image image = new Image();
            image.setFileName(data.getOriginalFilename());
            image.setUrl(Common.writeFile(data, pathClassPath, pathFolderUpload));
            image.setDescription("Create new account");
            image.setAccount(account);
            image.setCreateBy(Common.getUsernameLogin());
            image.setCreateAt(Common.getSystemDate());
            image.setUpdateBy(Common.getUsernameLogin());
            image.setUpdateAt(Common.getSystemDate());
            images.add(image);
        }
        return images;
    }

    public Account getAccountLogin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AccountPrincipal loginedUser = (AccountPrincipal) auth.getPrincipal();
        return accountsRespository.findByEmail(loginedUser.getEmail());
    }

    public List<Account> searchAccount(FormSearchAccount formSearchAccount) {

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

    public Object getObjectUpdate(Long accountId) {
        AccountForm accountForm = new AccountForm();
        Account account = accountsRespository.findByAccountIdAndIsDelete(accountId, Constant.IS_NOT_DELETE);
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
    
    @Transactional
    public boolean updateAccount(AccountForm accountForm) {
        if (accountForm == null) {
            return false;
        }
        Account account = accountsRespository.findByAccountIdAndIsDelete(accountForm.getAccountId(), Constant.IS_NOT_DELETE);
        if (account == null) {
            return false;
        }
        account.setFirstName(accountForm.getFirstName());
        account.setMiddleName(accountForm.getMiddleName());
        account.setLastName(accountForm.getLastName());
        account.setBirthDay(Common.stringToDate(accountForm.getBirthDay()));
        account.setEmail(accountForm.getEmail());
        account.setNumberPhone(accountForm.getNumberPhone());
        account.setGender(accountForm.getGender());
        account.setUpdateBy(Common.getUsernameLogin());
        account.setUpdateAt(Common.getSystemDate());
        if (accountsRespository.save(account) == null) {
            return false;
        } else {
            return true;
        }
    }
}
