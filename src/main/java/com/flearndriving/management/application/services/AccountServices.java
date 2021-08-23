package com.flearndriving.management.application.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.flearndriving.management.application.common.Common;
import com.flearndriving.management.application.common.Constant;
import com.flearndriving.management.application.common.MimeTypes;
import com.flearndriving.management.application.dto.AccountForm;
import com.flearndriving.management.application.dto.AccountLogin;
import com.flearndriving.management.application.dto.AccountUpdateForm;
import com.flearndriving.management.application.dto.FormSearchAccount;
import com.flearndriving.management.application.entities.Account;
import com.flearndriving.management.application.entities.Document;
import com.flearndriving.management.application.entities.Role;
import com.flearndriving.management.application.exception.BusinessException;
import com.flearndriving.management.application.respositories.AccountsRespository;
import com.flearndriving.management.application.respositories.DocumentRespository;
import com.flearndriving.management.application.respositories.RoleRespository;
import com.flearndriving.management.application.specification.AccountSpecification;
import com.flearndriving.management.application.utils.DateTimeUtils;
import com.flearndriving.management.application.utils.EncrytedPasswordUtils;

@Service
public class AccountServices {

    @Autowired
    private AccountsRespository accountsRespository;

    @Autowired
    private DocumentRespository documentRespository;

    @Autowired
    private RoleRespository roleRespository;
    
    @Autowired
    AmazonS3ClientService amazonS3ClientService;

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
        return accountsRespository.findByUserName(Common.getUsernameLogin());
    }

    public AccountLogin getBasicInfoAccountLogin() {
        String userName = Common.getUsernameLogin();
        AccountLogin accountLogin = accountsRespository.findBasicInfoByUserName(userName);
        if (accountLogin != null) {

            accountLogin.setUrlAvatar(documentRespository
                    .findUrlDocumentByTypeAndAccountId(Constant.TYPE_DOCUMENT_AVATAR, accountLogin.getAccountId()));
        }
        return accountLogin;
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
        return accountsRespository.findAll(conditions, pageable);
    }

    @Transactional
    public void deleteAccount(Long accountId) {
        Account account = accountsRespository.findByAccountId(accountId);
        if (account == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Người dùng không tồn tại!");
        }
        accountsRespository.delete(account);
    }

    public AccountUpdateForm getObjectUpdate(Long accountId) {
        AccountUpdateForm accountUpdateForm = new AccountUpdateForm();
        Account account = accountsRespository.findByAccountId(accountId);
        accountUpdateForm.setAccountId(account.getAccountId());
        accountUpdateForm.setFirstName(account.getFirstName());
        accountUpdateForm.setMiddleName(account.getMiddleName());
        accountUpdateForm.setLastName(account.getLastName());
        accountUpdateForm.setUserName(account.getUserName());
        accountUpdateForm.setBirthDay(account.getBirthDay() == null ? StringUtils.EMPTY : DateFormatUtils.format(account.getBirthDay(), Constant.FORMAT_DATE));
        accountUpdateForm.setNumberPhone(account.getNumberPhone());
        accountUpdateForm.setEmail(account.getEmail());
        accountUpdateForm.setGender(account.getGender());
        accountUpdateForm.setDescription(account.getDescription());
        accountUpdateForm.setRoleId(account.getRole().getRoleId());
        accountUpdateForm.setUrlAvatar(documentRespository
                .findUrlDocumentByTypeAndAccountId(Constant.TYPE_DOCUMENT_AVATAR, account.getAccountId()));
        return accountUpdateForm;
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

    public AccountForm getAccountLoginInfo() {
        AccountForm accountForm = new AccountForm();
        Account account = getAccountLogin();
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

    public List<Account> getDataReportAccountMonth() {
        Date today = new Date();
        Date firstDayOfMonthAgo = DateTimeUtils.getFirstDateOfMonth(DateTimeUtils.plusMonthToDate(today, -1));
        Date lastDayOfMonth = DateTimeUtils.getLastDateOfMonth(today);
        return accountsRespository.findReportByCreateDate(firstDayOfMonthAgo, lastDayOfMonth);
    }

    @Transactional
    public void uploadAvatar(MultipartFile file, Long accountId) {
        Account account = accountsRespository.findByAccountId(accountId);
        if (account == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Người dùng không tồn tại!");
        }
        if (file.isEmpty()) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "File không hợp lệ!");
        }
        Document document = documentRespository.findByTypeAndAccountId(Constant.TYPE_DOCUMENT_AVATAR,
                account.getAccountId());
        if (document != null) {
            document.setFileName(Common.generateFileName(file, Constant.DOCUMENT_ORTHER_LABEL));
            document.setOriginFileName(file.getOriginalFilename());
            document.setExtension(MimeTypes.lookupExt(file.getContentType()));
            document.setContentType(file.getContentType());
            document.setSize(file.getSize());
            document.setType(Constant.TYPE_DOCUMENT_AVATAR);
            document.setDescription("Ảnh đại diện");
            document.setCreateAt(Common.getSystemDate());
            document.setCreateBy(Common.getUsernameLogin());
            amazonS3ClientService.deleteFileFromS3Bucket(document.getPath());
            document.setPath(amazonS3ClientService.uploadFileToS3Bucket(file, document.getFileName()));
            documentRespository.save(document);
        } else {
            document = new Document();
            document.setFileName(Common.generateFileName(file, Constant.DOCUMENT_ORTHER_LABEL));
            document.setOriginFileName(file.getOriginalFilename());
            document.setExtension(MimeTypes.lookupExt(file.getContentType()));
            document.setContentType(file.getContentType());
            document.setSize(file.getSize());
            document.setType(Constant.TYPE_DOCUMENT_AVATAR);
            document.setDescription("Ảnh mô tả chương");
            document.setCreateAt(Common.getSystemDate());
            document.setCreateBy(Common.getUsernameLogin());
            document.setPath(amazonS3ClientService.uploadFileToS3Bucket(file, document.getFileName()));
            document.setAccount(account);
        }
        documentRespository.save(document);
    }
}
