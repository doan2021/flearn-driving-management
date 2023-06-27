package com.flearndriving.management.application.services;

import com.flearndriving.management.application.common.Constant;
import com.flearndriving.management.application.dto.request.ExamQuestionsForm;
import com.flearndriving.management.application.dto.request.FormSearchExamQuestions;
import com.flearndriving.management.application.entities.DrivingLicense;
import com.flearndriving.management.application.entities.Question;
import com.flearndriving.management.application.exception.BusinessException;
import com.flearndriving.management.application.respositories.DrivingLicenseRepository;
import com.flearndriving.management.application.respositories.ExamQuestionsRepository;
import com.flearndriving.management.application.respositories.QuestionsRepository;
import com.flearndriving.management.application.specification.ExamQuestionsSpecification;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamQuestionsServices {

    @Autowired
    ExamQuestionsRepository examQuestionsRepository;

    @Autowired
    DrivingLicenseRepository drivingLicenseRepository;

    @Autowired
    QuestionsRepository questionsRepository;

    @Autowired
    private final CommonServices commonServices;

    public List<ExamQuestions> findExamQuestionByDrivingLicenseId(Long drivingLicenseId) {
        DrivingLicense drivingLicense = drivingLicenseRepository.findByDrivingLicenseId(drivingLicenseId);
        if (drivingLicense == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Hạng bằng không tồn tại!");
        }
        return examQuestionsRepository.findByDrivingLicense(drivingLicense);
    }

    public ExamQuestions findByExamQuestionId(Long examQuestionId) {
        ExamQuestions examQuestions = examQuestionsRepository.findByExamQuestionsId(examQuestionId);
        if (examQuestions == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Đề thi không tồn tại!");
        }
        return examQuestions;
    }

    public Page<ExamQuestions> searchExamQuestions(FormSearchExamQuestions formSearchExamQuestions) {
        if (formSearchExamQuestions.getPageNumber() == null) {
            formSearchExamQuestions.setPageNumber(0);
        }
        Specification<ExamQuestions> conditions = Specification.where(null);
        if (formSearchExamQuestions != null) {
            if (StringUtils.isNotBlank(formSearchExamQuestions.getName())) {
                conditions = conditions.and(ExamQuestionsSpecification.hasName(formSearchExamQuestions.getName()));
            }
            if (StringUtils.isNotBlank(formSearchExamQuestions.getDescription())) {
                conditions = conditions
                        .and(ExamQuestionsSpecification.likeContent(formSearchExamQuestions.getDescription()));
            }

            if (StringUtils.isNotBlank(formSearchExamQuestions.getUpdateAtFrom())) {
                conditions = conditions
                        .and(ExamQuestionsSpecification.hasUpdateAtFrom(formSearchExamQuestions.getUpdateAtFrom()));
            }
            if (StringUtils.isNotBlank(formSearchExamQuestions.getUpdateAtTo())) {
                conditions = conditions
                        .and(ExamQuestionsSpecification.hasUpdateAtTo(formSearchExamQuestions.getUpdateAtTo()));
            }
        }

        Pageable pageable = PageRequest.of(formSearchExamQuestions.getPageNumber(), Constant.RECORD_PER_PAGE);
        Page<ExamQuestions> listExamQuestions = examQuestionsRepository.findAll(conditions, pageable);
        return listExamQuestions;
    }

    @Transactional
    public void createExamQuestions(ExamQuestionsForm examQuestionsForm) {
        if (examQuestionsForm == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Dữ liệu truyền vào không hợp lệ!");
        }
        DrivingLicense drivingLicense = drivingLicenseRepository.findByDrivingLicenseId(examQuestionsForm.getDrivingLicenseId());
        if (drivingLicense == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Hạng bằng không tồn tại!");
        }
        ExamQuestions examQuestions = new ExamQuestions();
        examQuestions.setName(examQuestionsForm.getName());
        examQuestions.setDescription(examQuestionsForm.getDescription());
        examQuestions.setDrivingLicense(drivingLicense);
        List<ExamQuestionsDetail> listExamQuestionDetail = new ArrayList<>();
        for (Long questionId : examQuestionsForm.getListIdQuestion()) {
            ExamQuestionsDetail examQuestionsDetail = new ExamQuestionsDetail();
            Question question = questionsRepository.findByQuestionId(questionId);
            if (question == null) {
                throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Câu hỏi số " + questionId + " không tồn tại!");
            } else {
                examQuestionsDetail.setQuestion(question);
            }
            examQuestionsDetail.setExamQuestions(examQuestions);
            listExamQuestionDetail.add(examQuestionsDetail);
        }

        for (Long questionId : examQuestionsForm.getListIdQuestionParalysis()) {
            ExamQuestionsDetail examQuestionsDetail = new ExamQuestionsDetail();
            Question question = questionsRepository.findByQuestionId(questionId);
            if (question == null) {
                throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Câu hỏi số " + questionId + " không tồn tại!");
            } else {
                examQuestionsDetail.setQuestion(question);
            }
            examQuestionsDetail.setExamQuestions(examQuestions);
            listExamQuestionDetail.add(examQuestionsDetail);
        }
        examQuestions.setListExamQuestionsDetail(listExamQuestionDetail);
        examQuestionsRepository.save(examQuestions);
    }

    public List<Question> findQuestionInExamQuestions(Long examQuestionsId) {
        return examQuestionsRepository.findQuestionInExamQuestionsId(examQuestionsId);
    }

    public List<Question> findQuestionParalysis() {
        return questionsRepository.findQuestionParalysis();
    }

    @Transactional
    public Long deleteExamQuestions(Long examQuestionsId) {
        ExamQuestions examQuestions = examQuestionsRepository.findByExamQuestionsId(examQuestionsId);
        if (examQuestions == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Đề thi không tồn tại!");
        }
        examQuestionsRepository.save(examQuestions);
        return examQuestions.getDrivingLicense().getId();
    }
}
