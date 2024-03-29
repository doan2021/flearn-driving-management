package com.flearndriving.management.application.specification;

import com.flearndriving.management.application.common.Common;
import com.flearndriving.management.application.entities.Exam;
import com.flearndriving.management.application.entities.Exam_;
import com.flearndriving.management.application.utils.DateTimeUtils;
import org.springframework.data.jpa.domain.Specification;

public class ExamSpecification {
    public static Specification<Exam> hasName(String name) {
        return (root, query, cb) -> cb.equal(root.get(Exam_.NAME), name);
    }

    public static Specification<Exam> likeContent(String description) {
        return (root, query, cb) -> cb.like(root.get(Exam_.DESCRIPTION), "%" + description + "%");
    }

    public static Specification<Exam> hasDateRegisExamEndFrom(String dateRegisExamEndFrom) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(Exam_.DATE_REGIS_EXAM_END), Common.stringToDate(dateRegisExamEndFrom));
    }

    public static Specification<Exam> hasDateRegisExamEndTo(String dateRegisExamEndTo) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(Exam_.DATE_REGIS_EXAM_END),
                DateTimeUtils.atEndOfDay(Common.stringToDate(dateRegisExamEndTo)));
    }

    public static Specification<Exam> hasUpdateFrom(String updateAtFrom) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(Exam_.LAST_MODIFIED_DATE), Common.stringToDate(updateAtFrom));
    }

    public static Specification<Exam> hasUpdateTo(String updateAtTo) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(Exam_.LAST_MODIFIED_DATE),
                DateTimeUtils.atEndOfDay(Common.stringToDate(updateAtTo)));
    }

    public static Specification<Exam> hasDescription(String description) {
        return (root, query, cb) -> cb.like(root.get(Exam_.DESCRIPTION), "%" + description + "%");
    }
}
