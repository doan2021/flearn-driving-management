package com.doanfpt.management.application.specification;

import org.springframework.data.jpa.domain.Specification;

import com.doanfpt.management.application.entities.DrivingLicense;
import com.doanfpt.management.application.entities.DrivingLicense_;

public class DrivingLicenseSpecification {
	public static Specification<DrivingLicense> hasName(String name) {
		return (root, query, cb) -> cb.equal(root.get(DrivingLicense_.NAME), name);
	}

	public static Specification<DrivingLicense> hasNumberQuestion(String numberQuestion) {
		return (root, query, cb) -> cb.equal(root.get(DrivingLicense_.NUMBER_QUESTION), numberQuestion);
	}

	public static Specification<DrivingLicense> hasNumberQuestionParalysis(String numberQuestionParalysis) {
		return (root, query, cb) -> cb.equal(root.get(DrivingLicense_.NUMBER_QUESTION_PARALYSIS),
				numberQuestionParalysis);
	}

	public static Specification<DrivingLicense> hasPrice(String price) {
		return (root, query, cb) -> cb.equal(root.get(DrivingLicense_.PRICE), price);
	}

	public static Specification<DrivingLicense> hasExamMinutes(String ExamMinutes) {
		return (root, query, cb) -> cb.equal(root.get(DrivingLicense_.EXAM_MINUTES), ExamMinutes);
	}

	public static Specification<DrivingLicense> hasNumberYearExpires(String numberYearExpires) {
		return (root, query, cb) -> cb.equal(root.get(DrivingLicense_.NUMBER_YEAR_EXPIRES), numberYearExpires);
	}

}
