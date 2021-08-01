package com.doanfpt.management.application.services;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.dto.FormSearchExamQuestions;
import com.doanfpt.management.application.entities.ExamQuestions;
import com.doanfpt.management.application.respositories.ExamQuestionsRepository;
import com.doanfpt.management.application.specification.ExamQuestionsSpecification;

@Service
public class ExamQuestionsServices {
	
	@Autowired
	ExamQuestionsRepository examQuestionsRepository;

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
                conditions = conditions.and(ExamQuestionsSpecification.likeContent(formSearchExamQuestions.getDescription()));
            }

            if (StringUtils.isNotBlank(formSearchExamQuestions.getUpdateAtFrom())) {
                conditions = conditions.and(ExamQuestionsSpecification.hasUpdateAtFrom(formSearchExamQuestions.getUpdateAtFrom()));
            }
            if (StringUtils.isNotBlank(formSearchExamQuestions.getUpdateAtTo())) {
                conditions = conditions.and(ExamQuestionsSpecification.hasUpdateAtTo(formSearchExamQuestions.getUpdateAtTo()));
            }
        }

        Pageable pageable = PageRequest.of(formSearchExamQuestions.getPageNumber(), Constant.RECORD_PER_PAGE);
        Page<ExamQuestions> listExamQuestions = examQuestionsRepository.findAll(conditions, pageable);
        return listExamQuestions;
    }

}
