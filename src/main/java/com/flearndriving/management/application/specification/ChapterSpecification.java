package com.flearndriving.management.application.specification;

import org.springframework.data.jpa.domain.Specification;

import com.flearndriving.management.application.common.Common;
import com.flearndriving.management.application.entities.Chapter;
import com.flearndriving.management.application.entities.Chapter_;
import com.flearndriving.management.application.utils.DateTimeUtils;

public class ChapterSpecification {
    public static Specification<Chapter> hasIndex(String index) {
        return (root, query, cb) -> cb.equal(root.get(Chapter_.INDEX), index);
    }
    
    public static Specification<Chapter> isDelete(boolean isDelete) {
        return (root, query, cb) -> cb.equal(root.get(Chapter_.IS_DELETE), isDelete);
    }
    
    public static Specification<Chapter> likeName(String name) {
        return (root, query, cb) -> cb.like(root.get(Chapter_.NAME), "%" + name + "%");
    }
    
    public static Specification<Chapter> hasUpdateAtFrom(String updateAtFrom) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(Chapter_.UPDATE_AT), Common.stringToDate(updateAtFrom));
    }
    
    public static Specification<Chapter> hasUpdateAtTo(String updateAtTo) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(Chapter_.UPDATE_AT), DateTimeUtils.atEndOfDay(Common.stringToDate(updateAtTo)));
    }
}
