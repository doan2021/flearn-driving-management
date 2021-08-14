package com.doanfpt.management.application.specification;

import org.springframework.data.jpa.domain.Specification;

import com.doanfpt.management.application.common.Common;
import com.doanfpt.management.application.entities.ExamQuestions;
import com.doanfpt.management.application.entities.ExamQuestions_;
import com.doanfpt.management.application.utils.DateTimeUtils;

public class ExamQuestionsSpecification {
	public static Specification<ExamQuestions> hasName(String name) {
		return (root, query, cb) -> cb.equal(root.get(ExamQuestions_.NAME), name);
	}

	public static Specification<ExamQuestions> likeContent(String content) {
		return (root, query, cb) -> cb.like(root.get(ExamQuestions_.DESCRIPTION), "%" + content + "%");
	}

	public static Specification<ExamQuestions> hasUpdateAtFrom(String updateAtFrom) {
		return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(ExamQuestions_.UPDATE_AT),
				Common.stringToDate(updateAtFrom));
	}

	public static Specification<ExamQuestions> hasUpdateAtTo(String updateAtTo) {
		return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(ExamQuestions_.UPDATE_AT),
				DateTimeUtils.atEndOfDay(Common.stringToDate(updateAtTo)));
	}
}
