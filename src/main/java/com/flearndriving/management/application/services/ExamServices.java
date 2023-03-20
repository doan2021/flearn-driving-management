package com.flearndriving.management.application.services;

import com.flearndriving.management.application.common.Common;
import com.flearndriving.management.application.common.Constant;
import com.flearndriving.management.application.dto.ExamForm;
import com.flearndriving.management.application.dto.ExamUpdateForm;
import com.flearndriving.management.application.dto.FormSearchExam;
import com.flearndriving.management.application.entities.DrivingLicense;
import com.flearndriving.management.application.entities.Exam;
import com.flearndriving.management.application.exception.BusinessException;
import com.flearndriving.management.application.respositories.DrivingLicenseRepository;
import com.flearndriving.management.application.respositories.ExamRepository;
import com.flearndriving.management.application.specification.ExamSpecification;
import com.flearndriving.management.application.utils.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ExamServices {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private DrivingLicenseRepository drivingLicenseRepository;

    @Autowired
    private final CommonServices commonServices;

    @Transactional
    public void createExam(ExamForm examForm) {
        if (Objects.isNull(examForm)) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Dữ liệu truyền vào không đúng");
        }
        Exam exam = new Exam();
        exam.setName(examForm.getName());
        exam.setDescription(examForm.getDescription());
        exam.setDateRegisExamEnd(DateTimeUtils.atEndOfDay(Common.stringToDate(examForm.getDateRegisExamEnd())));
        // TODO
        //Date dateExam = Common.addDays(exam.getDateRegisExamEnd(), Integer.parseInt(dataSetting.getDataSettingExamDate().getValue()));
        //exam.setDateExam(dateExam);
        DrivingLicense drivingLicense = drivingLicenseRepository.getOne(examForm.getDrivingLicenseId());
        exam.setDrivingLicense(drivingLicense);
        exam.setStatus(Constant.STS_EXAM_OPENING);
        exam.setCreateBy(commonServices.getUsernameLogin());
        exam.setCreateAt(Common.getSystemDate());
        exam.setUpdateBy(commonServices.getUsernameLogin());
        exam.setUpdateAt(Common.getSystemDate());
        examRepository.save(exam);
    }

    public ExamUpdateForm getObjectUpdate(Long examId) {
        Exam exam = examRepository.getOne(examId);
        if (exam == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Kỳ thi không tồn tại!");
        }
        ExamUpdateForm examUpdateForm = new ExamUpdateForm();
        examUpdateForm.setExamId(exam.getId());
        examUpdateForm.setName(exam.getName());
        examUpdateForm.setDescription(exam.getDescription());
        examUpdateForm.setStrDateRegisExamEnd(DateFormatUtils.format(exam.getDateRegisExamEnd(), Constant.FORMAT_DATE));
        examUpdateForm.setStrDateExam(DateFormatUtils.format(exam.getDateExam(), Constant.FORMAT_DATE));
        examUpdateForm.setDateRegisExamEnd(exam.getDateRegisExamEnd());
        examUpdateForm.setDateExam(exam.getDateExam());
        examUpdateForm.setStatus(exam.getStatus());
        examUpdateForm.setTypeDrivingLicense(exam.getDrivingLicense().getName());
        return examUpdateForm;
    }

    @Transactional
    public void updateExam(ExamUpdateForm examUpdateForm) {
        if (examUpdateForm == null) {
        	throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Dữ liệu truyền vào không đúng!");
        }
        Exam exam = new Exam();
        exam = examRepository.getOne(examUpdateForm.getExamId());
        if (exam == null) {
        	throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Kỳ thi không tồn tại!");
		}
        exam.setName(examUpdateForm.getName());
        exam.setDescription(examUpdateForm.getDescription());
        exam.setDateRegisExamEnd(examUpdateForm.getDateRegisExamEnd());
        Date dateExam = Common.addDays(examUpdateForm.getDateRegisExamEnd(), 15);
        exam.setDateExam(dateExam);
        exam.setUpdateBy(commonServices.getUsernameLogin());
        exam.setUpdateAt(Common.getSystemDate());
        examRepository.save(exam);
    }

    public Page<Exam> searchExam(FormSearchExam formSearchExam) {
        if (formSearchExam.getPageNumber() == null) {
            formSearchExam.setPageNumber(0);
        }
        Specification<Exam> conditions = Specification.where(null);
        if (formSearchExam != null) {
            if (StringUtils.isNotBlank(formSearchExam.getName())) {
                conditions = conditions.and(ExamSpecification.hasName(formSearchExam.getName()));
            }
            if (StringUtils.isNotBlank(formSearchExam.getDateRegisExamEndFrom())) {
                conditions = conditions
                        .and(ExamSpecification.hasDateRegisExamEndFrom(formSearchExam.getDateRegisExamEndFrom()));
            }
            if (StringUtils.isNotBlank(formSearchExam.getDateRegisExamEndTo())) {
                conditions = conditions
                        .and(ExamSpecification.hasDateRegisExamEndTo(formSearchExam.getDateRegisExamEndTo()));
            }
            if (StringUtils.isNotBlank(formSearchExam.getUpdateAtFrom())) {
                conditions = conditions.and(ExamSpecification.hasUpdateFrom(formSearchExam.getUpdateAtFrom()));
            }
            if (StringUtils.isNotBlank(formSearchExam.getUpdateAtTo())) {
                conditions = conditions.and(ExamSpecification.hasUpdateTo(formSearchExam.getUpdateAtTo()));
            }
            if (StringUtils.isNotBlank(formSearchExam.getDescription())) {
                conditions = conditions.and(ExamSpecification.hasDescription(formSearchExam.getDescription()));
            }
        }
        // Paging
        PageRequest pageable = PageRequest.of(formSearchExam.getPageNumber(), Constant.RECORD_PER_PAGE);
        Page<Exam> listExam = examRepository.findAll(conditions, pageable);
        return listExam;
    }

    @Transactional
    public void deleteExam(Long examId) {
        Exam exam = examRepository.getOne(examId);
        if (exam == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Kỳ thi không tồn tại!");
        }
        examRepository.delete(exam);
    }
    
    public Exam getOne(Long examId) {
        Exam exam = examRepository.getOne(examId);
        if (exam == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Kỳ thi không tồn tại!");
        }
        return examRepository.getOne(examId);
    }
    
    @Transactional
    public Exam cancelExam(Long examId) {
        Exam exam = examRepository.getOne(examId);
        if (exam == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_NOT_FOUND, "Kỳ thi không tồn tại!");
        }
        exam.setStatus(Constant.STS_EXAM_CANCEL);
        exam.setUpdateBy(commonServices.getUsernameLogin());
        exam.setUpdateAt(Common.getSystemDate());
        return examRepository.save(exam);
    }
}
