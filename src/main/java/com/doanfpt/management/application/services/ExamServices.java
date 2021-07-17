package com.doanfpt.management.application.services;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.doanfpt.management.application.common.Common;
import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.dto.ExamForm;
import com.doanfpt.management.application.dto.FormSearchExam;
import com.doanfpt.management.application.entities.DrivingLicense;
import com.doanfpt.management.application.entities.Exam;
import com.doanfpt.management.application.exception.ApplicationException;
import com.doanfpt.management.application.responsitories.DrivingLicenseRespository;
import com.doanfpt.management.application.responsitories.ExamRepository;
import com.doanfpt.management.application.specification.ExamSpecification;

@Service
public class ExamServices {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private DrivingLicenseRespository drivingLicenseRespository;

    public List<Exam> listAll() {
        return examRepository.findByIsDeleteAndIsTrial(false, false);
    }

    public void createExam(ExamForm examForm) {
        if (Objects.isNull(examForm)) {
            throw new ApplicationException(Constant.HTTPS_STATUS_CODE_500, "Dữ liệu truyền vào không đúng");
        }
        Exam exam = new Exam();
        exam.setName(examForm.getName());
        exam.setDescription(examForm.getDescription());
        exam.setDateRegisExamEnd(Common.stringToDate(examForm.getDateRegisExamEnd()));
        Date dateExam = Common.addDays(Common.stringToDate(examForm.getDateRegisExamEnd()), 15);
        exam.setDateExam(dateExam);
        DrivingLicense drivingLicense = drivingLicenseRespository.getOne(examForm.getDrivingLicenseId());
        exam.setDrivingLicense(drivingLicense);
        exam.setCreateBy(Common.getUsernameLogin());
        exam.setCreateAt(Common.getSystemDate());
        exam.setUpdateBy(Common.getUsernameLogin());
        exam.setUpdateAt(Common.getSystemDate());
        examRepository.save(exam);
    }

    public void updateExam(ExamForm examForm) {
        if (Objects.isNull(examForm)) {
            throw new ApplicationException(Constant.HTTPS_STATUS_CODE_500, "Dữ liệu truyền vào không đúng");
        }
        Exam exam = new Exam();
        exam = examRepository.getOne(examForm.getExamId());
        exam.setName(examForm.getName());
        exam.setDescription(examForm.getDescription());
        exam.setDateRegisExamEnd(Common.stringToDate(examForm.getDateRegisExamEnd()));
        Date dateExam = Common.addDays(Common.stringToDate(examForm.getDateRegisExamEnd()), 15);
        exam.setDateExam(dateExam);
        exam.setUpdateBy(Common.getUsernameLogin());
        exam.setUpdateAt(Common.getSystemDate());
        examRepository.save(exam);
    }

    public ExamForm getObjectUpdate(Long examId) {
        ExamForm examForm = new ExamForm();
        Exam exam = examRepository.getOne(examId);
        examForm.setExamId(exam.getExamId());
        examForm.setName(exam.getName());
        examForm.setDescription(exam.getDescription());
        examForm.setDateRegisExamEnd(DateFormatUtils.format(exam.getDateRegisExamEnd(), Constant.FORMAT_DATE));
        examForm.setDateExam(DateFormatUtils.format(exam.getDateExam(), Constant.FORMAT_DATE));
        examForm.setUpdateAt(DateFormatUtils.format(exam.getUpdateAt(), Constant.FORMAT_DATE_TIME));
        examForm.setUpdateBy(exam.getUpdateBy());
        examForm.setCreateAt(DateFormatUtils.format(exam.getCreateAt(), Constant.FORMAT_DATE_TIME));
        examForm.setCreateBy(exam.getCreateBy());
        return examForm;

    }

    public Page<Exam> getAllExam(Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = 0;
        }
        Specification<Exam> conditions = Specification.where(ExamSpecification.isDelete(false));
        PageRequest pageable = PageRequest.of(pageNumber, Constant.RECORD_PER_PAGE);
        Page<Exam> listExam = examRepository.findAll(conditions, pageable);
        return listExam;
    }

    public Page<Exam> searchExam(FormSearchExam formSearchExam) {
        if (formSearchExam.getPageNumber() == null) {
            formSearchExam.setPageNumber(0);
        }
        // Init condition with is_delete
        Specification<Exam> conditions = Specification.where(ExamSpecification.isDelete(false))
                .and(ExamSpecification.isTrial(false));
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

        PageRequest pageable = PageRequest.of(formSearchExam.getPageNumber(), Constant.RECORD_PER_PAGE);
        Page<Exam> listExam = examRepository.findAll(conditions, pageable);
        return listExam;
    }

    @Transactional
    public void deleteExam(Long examId) {
        Exam exam = examRepository.getOne(examId);
        exam.setDelete(true);
        exam.setUpdateBy(Common.getUsernameLogin());
        exam.setUpdateAt(Common.getSystemDate());
        examRepository.save(exam);
    }
}
