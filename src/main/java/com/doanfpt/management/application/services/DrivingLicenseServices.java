package com.doanfpt.management.application.services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.dto.FormSearchDrivingLicense;
import com.doanfpt.management.application.dto.FormSearchExamQuestions;
import com.doanfpt.management.application.entities.DrivingLicense;
import com.doanfpt.management.application.entities.ExamQuestions;
import com.doanfpt.management.application.responsitories.DrivingLicenseRespository;
import com.doanfpt.management.application.specification.DrivingLicenseSpecification;
import com.doanfpt.management.application.specification.ExamQuestionsSpecification;

@Service
public class DrivingLicenseServices {

    @Autowired
    private DrivingLicenseRespository drivingLicenseRespository;

    public List<DrivingLicense> findAll() {
        return drivingLicenseRespository.findAll();
    }

    public DrivingLicense findById(Long drivingLicenseId) {
        return drivingLicenseRespository.getOne(drivingLicenseId);
    }
    
    public Page<DrivingLicense> searchDrivingLicense(FormSearchDrivingLicense formSearchDrivingLicense) {
        if (formSearchDrivingLicense.getPageNumber() == null) {
        	formSearchDrivingLicense.setPageNumber(0);
        }
        // Init condition with is_delete
        Specification<DrivingLicense> conditions = Specification.where(DrivingLicenseSpecification.isDelete(false));
        if (formSearchDrivingLicense != null) {
            if (StringUtils.isNotBlank(formSearchDrivingLicense.getName())) {
                conditions = conditions.and(DrivingLicenseSpecification.hasName(formSearchDrivingLicense.getName()));
            }
            if (StringUtils.isNotBlank(formSearchDrivingLicense.getPrice())) {
                conditions = conditions.and(DrivingLicenseSpecification.hasPrice(formSearchDrivingLicense.getPrice()));
            }
            
 


        }

        Pageable pageable = PageRequest.of(formSearchExamQuestions.getPageNumber(), Constant.RECORD_PER_PAGE);
        Page<ExamQuestions> listExamQuestions = examQuestionsRepository.findAll(conditions, pageable);
        return listExamQuestions;
    }
}
