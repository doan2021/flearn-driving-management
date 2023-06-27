package com.flearndriving.management.application.services;

import com.flearndriving.management.application.common.Common;
import com.flearndriving.management.application.common.Constant;
import com.flearndriving.management.application.common.MimeTypes;
import com.flearndriving.management.application.converter.CustomerConverter;
import com.flearndriving.management.application.dto.request.CustomerLogin;
import com.flearndriving.management.application.dto.request.CustomerRequest;
import com.flearndriving.management.application.dto.request.CustomerUpdateForm;
import com.flearndriving.management.application.dto.request.FormSearchCustomer;
import com.flearndriving.management.application.entities.Customer;
import com.flearndriving.management.application.entities.Document;
import com.flearndriving.management.application.exception.BusinessException;
import com.flearndriving.management.application.respositories.CustomerRepository;
import com.flearndriving.management.application.respositories.DocumentRepository;
import com.flearndriving.management.application.specification.CustomerSpecification;
import com.flearndriving.management.application.utils.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerServices {

    @Resource
    private CustomerRepository customerRepository;

    @Resource
    private DocumentRepository documentRepository;

    @Resource
    private CommonServices commonServices;

    @Resource
    CustomerConverter customerConverter;

    @Transactional
    public void createCustomer(CustomerRequest request) {
        customerRepository.save(customerConverter.buildCustomer(request));
    }

    public Customer getCustomerLogin() {
        return customerRepository.findByUserName(commonServices.getUsernameLogin());
    }

    public CustomerLogin getCustomerLoginDetail() {
        String userName = commonServices.getUsernameLogin();
        Customer customer = customerRepository.findBasicInfoByUserNameAdmin(userName);
        CustomerLogin customerInfo = new CustomerLogin();
        if (Objects.nonNull(customer)) {
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

    public Page<Customer> searchCustomer(FormSearchCustomer formSearchcustomer) {
        // Init pageNum
        if (Objects.isNull(formSearchcustomer.getPageNumber())) {
            formSearchcustomer.setPageNumber(0);
        }
        Specification<Customer> conditions = Specification.where(CustomerSpecification.isDelete(false)
                .and(CustomerSpecification.notEqualUserName(commonServices.getUsernameLogin())));
        if (Objects.nonNull(formSearchcustomer)) {
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
    public void deleteCustomer(Long customerId) {
        Customer customer = customerRepository.getOne(customerId);
        if (customer == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Người dùng không tồn tại!");
        }
        customer.setDelete(true);
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
        customer.setDescription(customerUpdateForm.getDescription());
        customerRepository.save(customer);
    }

    public CustomerRequest getCustomerLoginInfo() {
        CustomerRequest customerForm = new CustomerRequest();
        Customer customer = getCustomerLogin();
        customerForm.setId(customer.getId());
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
        if (Objects.isNull(customer)) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Người dùng không tồn tại!");
        }
        if (file.isEmpty()) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "File không hợp lệ!");
        }
        Document document = documentRepository.findByTypeAndCustomerId(Constant.TYPE_DOCUMENT_AVATAR,
                customer.getId());
        if (Objects.isNull(document)) {
            handleInfoFile(file, document);
            document.setDescription("Ảnh đại diện");
            // TODO Upload Image
            //amazonS3ClientService.deleteFileFromS3Bucket(document.getPath());
            //document.setPath(amazonS3ClientService.uploadFileToS3Bucket(file, document.getFileName()));
            documentRepository.save(document);
        } else {
            document = new Document();
            handleInfoFile(file, document);
            document.setDescription("Ảnh mô tả chương");
            // TODO Upload Image
            //document.setPath(amazonS3ClientService.uploadFileToS3Bucket(file, document.getFileName()));
            document.setCustomer(customer);
        }
        documentRepository.save(document);
    }

    private void handleInfoFile(MultipartFile file, Document document) {
        document.setFileName(Common.generateFileName(file, Constant.DOCUMENT_ORTHER_LABEL));
        document.setOriginFileName(file.getOriginalFilename());
        document.setExtension(MimeTypes.lookupExt(file.getContentType()));
        document.setContentType(file.getContentType());
        document.setSize(file.getSize());
        document.setType(Constant.TYPE_DOCUMENT_AVATAR);
    }
}
