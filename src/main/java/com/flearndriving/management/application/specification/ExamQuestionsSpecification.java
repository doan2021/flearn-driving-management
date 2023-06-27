package com.flearndriving.management.application.specification;

import com.flearndriving.management.application.common.Common;
import com.flearndriving.management.application.entities.ExamQuestions_;
import com.flearndriving.management.application.utils.DateTimeUtils;
import org.springframework.data.jpa.domain.Specification;

public class ExamQuestionsSpecification {
    public static Specification<ExamQuestions> hasName(String name) {
        return (root, query, cb) -> cb.equal(root.get(ExamQuestions_.NAME), name);
    }

    public static Specification<ExamQuestions> likeContent(String content) {
        return (root, query, cb) -> cb.like(root.get(ExamQuestions_.DESCRIPTION), "%" + content + "%");
    }

    public static Specification<ExamQuestions> hasUpdateAtFrom(String updateAtFrom) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(ExamQuestions_.LAST_MODIFIED_DATE),
                Common.stringToDate(updateAtFrom));
    }

    public static Specification<ExamQuestions> hasUpdateAtTo(String updateAtTo) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(ExamQuestions_.LAST_MODIFIED_DATE),
                DateTimeUtils.atEndOfDay(Common.stringToDate(updateAtTo)));
    }
}
