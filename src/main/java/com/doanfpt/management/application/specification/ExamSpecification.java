package com.doanfpt.management.application.specification;

import org.springframework.data.jpa.domain.Specification;

import com.doanfpt.management.application.common.Common;
import com.doanfpt.management.application.entities.Exam;
import com.doanfpt.management.application.entities.Exam_;

public class ExamSpecification {

	public static Specification<Exam> hasName(String name) {
		return (root, query, cb) -> cb.equal(root.get(Exam_.NAME), name);
	}

	public static Specification<Exam> isDelete(boolean isDelete) {
		return (root, query, cb) -> cb.equal(root.get(Exam_.IS_DELETE), isDelete);
	}
	
	public static Specification<Exam> isTrial(boolean isTrial) {
		return (root, query, cb) -> cb.equal(root.get(Exam_.IS_TRIAL), isTrial);
	}

	public static Specification<Exam> likeContent(String description) {
		return (root, query, cb) -> cb.like(root.get(Exam_.DESCRIPTION), "%" + description + "%");
	}

	public static Specification<Exam> hasDateRegisExamStartFrom(String dateRegisExamStartFrom) {
		return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(Exam_.DATE_REGIS_EXAM_START), Common.stringToDate(dateRegisExamStartFrom));
	}

	public static Specification<Exam> hasDateRegisExamStartTo(String dateRegisExamStartTo) {
		return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(Exam_.DATE_REGIS_EXAM_START), Common.stringToDate(dateRegisExamStartTo));
	}

	public static Specification<Exam> hasDateRegisExamEndFrom(String dateRegisExamEndFrom) {
		return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(Exam_.DATE_REGIS_EXAM_END), Common.stringToDate(dateRegisExamEndFrom));
	}

	public static Specification<Exam> hasDateRegisExamEndTo(String dateRegisExamEndTo) {
		return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(Exam_.DATE_REGIS_EXAM_END), Common.stringToDate(dateRegisExamEndTo));
	}

	public static Specification<Exam> hasUpdateFrom(String updateAtFrom) {
		return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(Exam_.UPDATE_AT), Common.stringToDate(updateAtFrom));
	}
	public static Specification<Exam> hasUpdateTo(String updateAtTo) {
		return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(Exam_.UPDATE_AT), Common.stringToDate(updateAtTo));
	}

}
