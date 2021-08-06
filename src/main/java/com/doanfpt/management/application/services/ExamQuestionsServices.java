package com.doanfpt.management.application.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.doanfpt.management.application.common.Common;
import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.dto.ExamQuestionsForm;
import com.doanfpt.management.application.dto.FormSearchExamQuestions;
import com.doanfpt.management.application.entities.DrivingLicense;
import com.doanfpt.management.application.entities.ExamQuestions;
import com.doanfpt.management.application.entities.ExamQuestionsDetail;
import com.doanfpt.management.application.entities.Question;
import com.doanfpt.management.application.exception.BusinessException;
import com.doanfpt.management.application.respositories.DrivingLicenseRespository;
import com.doanfpt.management.application.respositories.ExamQuestionsRepository;
import com.doanfpt.management.application.respositories.QuestionsRespository;
import com.doanfpt.management.application.specification.ExamQuestionsSpecification;

@Service
public class ExamQuestionsServices {

    @Autowired
    ExamQuestionsRepository examQuestionsRepository;
    
    @Autowired
    DrivingLicenseRespository drivingLicenseRespository;
    
    @Autowired
    QuestionsRespository questionsRespository;

    public Page<ExamQuestions> searchExamQuestions(FormSearchExamQuestions formSearchExamQuestions) {
        if (formSearchExamQuestions.getPageNumber() == null) {
            formSearchExamQuestions.setPageNumber(0);
        }
        // Init condition with is_delete
        Specification<ExamQuestions> conditions = Specification.where(ExamQuestionsSpecification.isDelete(false));
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
        DrivingLicense drivingLicense = drivingLicenseRespository.findByDrivingLicenseIdAndIsDelete(examQuestionsForm.getDrivingLicenseId(), Constant.IS_NOT_DELETE);
        if (drivingLicense == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Hạng bằng không tồn tại!");
        }
        ExamQuestions examQuestions = new ExamQuestions();
        examQuestions.setName(examQuestionsForm.getName());
        examQuestions.setDescription(examQuestionsForm.getDescription());
        examQuestions.setDrivingLicense(drivingLicense);
        examQuestions.setCreateBy(Common.getUsernameLogin());
        examQuestions.setCreateAt(Common.getSystemDate());
        examQuestions.setUpdateBy(Common.getUsernameLogin());
        examQuestions.setUpdateAt(Common.getSystemDate());
        List<ExamQuestionsDetail> listExamQuestionDetail = new ArrayList<>();
        for (Long questionId : examQuestionsForm.getListIdQuestion()) {
            ExamQuestionsDetail examQuestionsDetail = new ExamQuestionsDetail();
            Question question = questionsRespository.findByQuestionIdAndIsDelete(questionId, Constant.IS_NOT_DELETE);
            if (question == null) {
                throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Câu hỏi số " + questionId + " không tồn tại!");
            } else {
                examQuestionsDetail.setQuestion(question);
            }
            examQuestionsDetail.setExamQuestions(examQuestions);
            examQuestionsDetail.setCreateBy(Common.getUsernameLogin());
            examQuestionsDetail.setCreateAt(Common.getSystemDate());
            examQuestionsDetail.setUpdateBy(Common.getUsernameLogin());
            examQuestionsDetail.setUpdateAt(Common.getSystemDate());
            listExamQuestionDetail.add(examQuestionsDetail);
        }
        
        for (Long questionId : examQuestionsForm.getListIdQuestionParalysis()) {
            ExamQuestionsDetail examQuestionsDetail = new ExamQuestionsDetail();
            Question question = questionsRespository.findByQuestionIdAndIsDelete(questionId, Constant.IS_NOT_DELETE);
            if (question == null) {
                throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Câu hỏi số " + questionId + " không tồn tại!");
            } else {
                examQuestionsDetail.setQuestion(question);
            }
            examQuestionsDetail.setExamQuestions(examQuestions);
            examQuestionsDetail.setCreateBy(Common.getUsernameLogin());
            examQuestionsDetail.setCreateAt(Common.getSystemDate());
            examQuestionsDetail.setUpdateBy(Common.getUsernameLogin());
            examQuestionsDetail.setUpdateAt(Common.getSystemDate());
            listExamQuestionDetail.add(examQuestionsDetail);
        }
        examQuestions.setListExamQuestionsDetail(listExamQuestionDetail);
        examQuestionsRepository.save(examQuestions);
    }
}
