package com.doanfpt.management.application.services;

import java.util.Date;
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
import com.doanfpt.management.application.component.DataSettingComponent;
import com.doanfpt.management.application.dto.ExamForm;
import com.doanfpt.management.application.dto.ExamUpdateForm;
import com.doanfpt.management.application.dto.FormSearchExam;
import com.doanfpt.management.application.entities.DrivingLicense;
import com.doanfpt.management.application.entities.Exam;
import com.doanfpt.management.application.exception.BusinessException;
import com.doanfpt.management.application.responsitories.DrivingLicenseRespository;
import com.doanfpt.management.application.responsitories.ExamRepository;
import com.doanfpt.management.application.specification.ExamSpecification;

@Service
public class ExamServices {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private DrivingLicenseRespository drivingLicenseRespository;
    
    @Autowired
    private DataSettingComponent dataSetting;

    @Transactional
    public void createExam(ExamForm examForm) {
        if (Objects.isNull(examForm)) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Dữ liệu truyền vào không đúng");
        }
        Exam exam = new Exam();
        exam.setName(examForm.getName());
        exam.setDescription(examForm.getDescription());
        exam.setDateRegisExamEnd(Common.getLastOfTheDate(Common.stringToDate(examForm.getDateRegisExamEnd())));
        // Get số ngày từ data setting
        Date dateExam = Common.addDays(exam.getDateRegisExamEnd(), Integer.parseInt(dataSetting.getDataSettingExamDate().getValue()));
        exam.setDateExam(dateExam);
        DrivingLicense drivingLicense = drivingLicenseRespository.getOne(examForm.getDrivingLicenseId());
        exam.setDrivingLicense(drivingLicense);
        exam.setStatus(Constant.STS_EXAM_OPENING);
        exam.setCreateBy(Common.getUsernameLogin());
        exam.setCreateAt(Common.getSystemDate());
        exam.setUpdateBy(Common.getUsernameLogin());
        exam.setUpdateAt(Common.getSystemDate());
        examRepository.save(exam);
    }

    public ExamUpdateForm getObjectUpdate(Long examId) {
        if (!existsById(examId)) {
            throw new BusinessException(404, "Kỳ thi không tồn tại.");
        }
        ExamUpdateForm examUpdateForm = new ExamUpdateForm();
        Exam exam = examRepository.findByExamIdAndIsDelete(examId, Constant.IS_NOT_DELETE);
        examUpdateForm.setExamId(exam.getExamId());
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
    public Exam updateExam(ExamUpdateForm examUpdateForm) {
        if (Objects.isNull(examUpdateForm)) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Dữ liệu truyền vào không đúng");
        }
        Exam exam = new Exam();
        exam = examRepository.findByExamIdAndIsDelete(examUpdateForm.getExamId(), Constant.IS_NOT_DELETE);
        exam.setName(examUpdateForm.getName());
        exam.setDescription(examUpdateForm.getDescription());
        exam.setDateRegisExamEnd(Common.stringToDate(examUpdateForm.getStrDateRegisExamEnd()));
        Date dateExam = Common.addDays(Common.stringToDate(examUpdateForm.getStrDateRegisExamEnd()), 15);
        exam.setDateExam(dateExam);
        exam.setUpdateBy(Common.getUsernameLogin());
        exam.setUpdateAt(Common.getSystemDate());
        return examRepository.save(exam);
    }

    public Page<Exam> searchExam(FormSearchExam formSearchExam) {
        if (formSearchExam.getPageNumber() == null) {
            formSearchExam.setPageNumber(0);
        }
        // Init condition with is_delete
        Specification<Exam> conditions = Specification.where(ExamSpecification.isDelete(false));
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
        // Phân trang với jpa
        PageRequest pageable = PageRequest.of(formSearchExam.getPageNumber(), Constant.RECORD_PER_PAGE);
        Page<Exam> listExam = examRepository.findAll(conditions, pageable);
        return listExam;
    }

    @Transactional
    public void deleteExam(Long examId) {
        if (!existsById(examId)) {
            throw new BusinessException(404, "Kỳ thi không tồn tại.");
        }
        Exam exam = examRepository.findByExamIdAndIsDelete(examId, Constant.IS_NOT_DELETE);
        exam.setDelete(true);
        exam.setUpdateBy(Common.getUsernameLogin());
        exam.setUpdateAt(Common.getSystemDate());
        examRepository.save(exam);
    }
    
    public Exam getOne(Long examId) {
        if (!existsById(examId)) {
            throw new BusinessException(404, "Kỳ thi không tồn tại.");
        }
        return examRepository.findByExamIdAndIsDelete(examId, Constant.IS_NOT_DELETE);
    }
    
    @Transactional
    public Exam cancelExam(Long examId) {
        if (!existsById(examId)) {
            throw new BusinessException(404, "Kỳ thi không tồn tại.");
        }
        Exam exam = examRepository.findByExamIdAndIsDelete(examId, Constant.IS_NOT_DELETE);
        exam.setStatus(Constant.STS_EXAM_CANCEL);
        exam.setUpdateBy(Common.getUsernameLogin());
        exam.setUpdateAt(Common.getSystemDate());
        return examRepository.save(exam);
    }
    
    public Boolean existsById(Long examId) {
        return examRepository.existsByExamIdAndIsDelete(examId, Constant.IS_NOT_DELETE);
    }
}
