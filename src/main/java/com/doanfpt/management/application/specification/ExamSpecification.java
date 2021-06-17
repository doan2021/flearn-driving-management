package com.doanfpt.management.application.specification;

import org.springframework.data.jpa.domain.Specification;

import com.doanfpt.management.application.entities.Exam;
import com.doanfpt.management.application.entities.Exam_;

public class ExamSpecification {
	
	public static Specification<Exam> hasName(String name) {
		return (root, query, cb) -> cb.equal(root.get(Exam_.NAME), name);
	}

	public static Specification<Exam> isDelete(boolean isDelete) {
		return (root, query, cb) -> cb.equal(root.get(Exam_.IS_DELETE), isDelete);
	}

	public static Specification<Exam> hasDescription(String description) {
        return (root, query, cb) -> cb.like(root.get(Exam_.DESCRIPTION), "%" + description + "%");
	}
	
}
