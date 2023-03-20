package com.flearndriving.management.application.services;

import com.flearndriving.management.application.common.Common;
import com.flearndriving.management.application.common.Constant;
import com.flearndriving.management.application.common.MimeTypes;
import com.flearndriving.management.application.dto.CustomerForm;
import com.flearndriving.management.application.dto.CustomerLogin;
import com.flearndriving.management.application.dto.CustomerUpdateForm;
import com.flearndriving.management.application.dto.FormSearchCustomer;
import com.flearndriving.management.application.entities.Customer;
import com.flearndriving.management.application.entities.Document;
import com.flearndriving.management.application.entities.Role;
import com.flearndriving.management.application.exception.BusinessException;
import com.flearndriving.management.application.respositories.CustomerRepository;
import com.flearndriving.management.application.respositories.DocumentRepository;
import com.flearndriving.management.application.respositories.RoleRepository;
import com.flearndriving.management.application.specification.CustomerSpecification;
import com.flearndriving.management.application.utils.DateTimeUtils;
import com.flearndriving.management.application.utils.EncrytedPasswordUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServices {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private final CommonServices commonServices;

    @Transactional
    public void createCustomer(CustomerForm customerForm) {
        String encrytedPassword = EncrytedPasswordUtils.encrytePassword(customerForm.getPassword());
        Customer customer = new Customer();
        customer.setUserName(customerForm.getUserName());
        customer.setFirstName(customerForm.getFirstName());
        customer.setMiddleName(customerForm.getMiddleName());
        customer.setLastName(customerForm.getLastName());
        customer.setBirthDay(Common.stringToDate(customerForm.getBirthDay()));
        customer.setEmail(customerForm.getEmail());
        customer.setNumberPhone(customerForm.getNumberPhone());
        customer.setEncrytedPassword(encrytedPassword);
        customer.setGender(customerForm.getGender());
        Role role = roleRepository.getOne(customerForm.getRoleId());
        customer.setRole(role);
        customer.setCreateBy(commonServices.getUsernameLogin());
        customer.setCreateAt(Common.getSystemDate());
        customer.setUpdateBy(commonServices.getUsernameLogin());
        customer.setUpdateAt(Common.getSystemDate());
        customerRepository.save(customer);
    }

    public Customer getcustomerLogin() {
        return customerRepository.findByUserNameAdmin(commonServices.getUsernameLogin());
    }

    public CustomerLogin getBasicInfocustomerLogin() {
        String userName = commonServices.getUsernameLogin();
        Customer customer = customerRepository.findBasicInfoByUserNameAdmin(userName);
        CustomerLogin customerInfo = new CustomerLogin();
        if (customer != null) {
            customerInfo.setCustomerId(customer.getId());
            customerInfo.setUserName(customer.getUserName());
            customerInfo.setFirstName(customer.getFirstName());
            customerInfo.setMiddleName(customer.getMiddleName());
            customerInfo.setLastName(customer.getLastName());
            customerInfo.setEmail(customer.getEmail());
            customerInfo.setDescription(customer.getDescription());
            customerInfo.setUrlAvatar(documentRepository.findUrlDocumentByTypeAndCustomerId(Constant.TYPE_DOCUMENT_AVATAR,
                    customer.getId()));
        }
        return customerInfo;
    }

    public Page<Customer> searchcustomer(FormSearchCustomer formSearchcustomer) {
        // Init pageNum
        if (formSearchcustomer.getPageNumber() == null) {
            formSearchcustomer.setPageNumber(0);
        }
        Specification<Customer> conditions = Specification.where(CustomerSpecification.isDelete(false)
                .and(CustomerSpecification.notEqualUserName(commonServices.getUsernameLogin())));
        if (formSearchcustomer != null) {
            if (StringUtils.isNotBlank(formSearchcustomer.getEmail())) {
                conditions = conditions.and(CustomerSpecification.hasEmail(formSearchcustomer.getEmail()));
            }
            if (StringUtils.isNotBlank(formSearchcustomer.getUserName())) {
                conditions = conditions.and(CustomerSpecification.hasUserName(formSearchcustomer.getUserName()));
            }

            if (StringUtils.isNotBlank(formSearchcustomer.getFullName())) {
                conditions = conditions.and(CustomerSpecification.likeFullName(formSearchcustomer.getFullName()));
            }

            if (formSearchcustomer.getGender() != null) {
                conditions = conditions.and(CustomerSpecification.hasGender(formSearchcustomer.getGender()));
            }
        }
        PageRequest pageable = PageRequest.of(formSearchcustomer.getPageNumber(), Constant.RECORD_PER_PAGE);
        return customerRepository.findAll(conditions, pageable);
    }

    @Transactional
    public void deletecustomer(Long customerId) {
        Customer customer = customerRepository.getOne(customerId);
        if (customer == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Người dùng không tồn tại!");
        }
        customer.setIsDelete(true);
        customer.setUpdateBy(commonServices.getUsernameLogin());
        customer.setUpdateAt(Common.getSystemDate());
        customerRepository.save(customer);
    }

    public CustomerUpdateForm getObjectUpdate(Long customerId) {
        CustomerUpdateForm customerUpdateForm = new CustomerUpdateForm();
        Customer customer = customerRepository.getOne(customerId);
        customerUpdateForm.setCustomerId(customer.getId());
        customerUpdateForm.setFirstName(customer.getFirstName());
        customerUpdateForm.setMiddleName(customer.getMiddleName());
        customerUpdateForm.setLastName(customer.getLastName());
        customerUpdateForm.setUserName(customer.getUserName());
        customerUpdateForm.setBirthDay(customer.getBirthDay() == null ? StringUtils.EMPTY
                : DateFormatUtils.format(customer.getBirthDay(), Constant.FORMAT_DATE));
        customerUpdateForm.setNumberPhone(customer.getNumberPhone());
        customerUpdateForm.setEmail(customer.getEmail());
        customerUpdateForm.setGender(customer.getGender());
        customerUpdateForm.setDescription(customer.getDescription());
        customerUpdateForm.setRoleId(customer.getRole().getId());
        customerUpdateForm.setUrlAvatar(documentRepository
                .findUrlDocumentByTypeAndCustomerId(Constant.TYPE_DOCUMENT_AVATAR, customer.getId()));
        return customerUpdateForm;
    }

    @Transactional
    public void updateCustomer(CustomerUpdateForm customerUpdateForm) {
        if (customerUpdateForm == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Dữ liệu truyền vào không đúng!");
        }
        Customer customer = customerRepository.getOne(customerUpdateForm.getCustomerId());
        if (customer == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Người dùng không tồn tại!");
        }
        customer.setFirstName(customerUpdateForm.getFirstName());
        customer.setMiddleName(customerUpdateForm.getMiddleName());
        customer.setLastName(customerUpdateForm.getLastName());
        customer.setBirthDay(Common.stringToDate(customerUpdateForm.getBirthDay()));
        customer.setEmail(customerUpdateForm.getEmail());
        customer.setNumberPhone(customerUpdateForm.getNumberPhone());
        customer.setGender(customerUpdateForm.getGender());
        customer.setUpdateBy(commonServices.getUsernameLogin());
        customer.setUpdateAt(Common.getSystemDate());
        customer.setDescription(customerUpdateForm.getDescription());
        customerRepository.save(customer);
    }

    public CustomerForm getcustomerLoginInfo() {
        CustomerForm customerForm = new CustomerForm();
        Customer customer = getcustomerLogin();
        customerForm.setCustomerId(customer.getId());
        customerForm.setFirstName(customer.getFirstName());
        customerForm.setMiddleName(customer.getMiddleName());
        customerForm.setLastName(customer.getLastName());
        customerForm.setUserName(customer.getUserName());
        customerForm.setBirthDay(DateFormatUtils.format(customer.getBirthDay(), Constant.FORMAT_DATE));
        customerForm.setNumberPhone(customer.getNumberPhone());
        customerForm.setEmail(customer.getEmail());
        customerForm.setGender(customer.getGender());
        customerForm.setRoleId(customer.getRole().getId());
        return customerForm;
    }

    public Integer countCustomer() {
        return customerRepository.countCustomerUser();
    }

    public List<Customer> getDataReportCustomerMonth() {
        Date today = new Date();
        Date firstDayOfMonthAgo = DateTimeUtils.getFirstDateOfMonth(DateTimeUtils.plusMonthToDate(today, -1));
        Date lastDayOfMonth = DateTimeUtils.getLastDateOfMonth(today);
        return customerRepository.findReportByCreateDate(firstDayOfMonthAgo, lastDayOfMonth);
    }

    @Transactional
    public void uploadAvatar(MultipartFile file, Long customerId) {
        Customer customer = customerRepository.getOne(customerId);
        if (customer == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Người dùng không tồn tại!");
        }
        if (file.isEmpty()) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "File không hợp lệ!");
        }
        Document document = documentRepository.findByTypeAndCustomerId(Constant.TYPE_DOCUMENT_AVATAR,
                customer.getId());
        if (document != null) {
            document.setFileName(Common.generateFileName(file, Constant.DOCUMENT_ORTHER_LABEL));
            document.setOriginFileName(file.getOriginalFilename());
            document.setExtension(MimeTypes.lookupExt(file.getContentType()));
            document.setContentType(file.getContentType());
            document.setSize(file.getSize());
            document.setType(Constant.TYPE_DOCUMENT_AVATAR);
            document.setDescription("Ảnh đại diện");
            document.setCreateAt(Common.getSystemDate());
            document.setCreateBy(commonServices.getUsernameLogin());
            // TODO Upload Image
            //amazonS3ClientService.deleteFileFromS3Bucket(document.getPath());
            //document.setPath(amazonS3ClientService.uploadFileToS3Bucket(file, document.getFileName()));
            documentRepository.save(document);
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
            document.setCreateBy(commonServices.getUsernameLogin());
            // TODO Upload Image
            //document.setPath(amazonS3ClientService.uploadFileToS3Bucket(file, document.getFileName()));
            document.setCustomer(customer);
        }
        documentRepository.save(document);
    }
}
