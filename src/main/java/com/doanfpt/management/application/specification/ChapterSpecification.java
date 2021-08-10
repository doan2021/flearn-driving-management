package com.doanfpt.management.application.specification;

import org.springframework.data.jpa.domain.Specification;

import com.doanfpt.management.application.common.Common;
import com.doanfpt.management.application.entities.Chapter;
import com.doanfpt.management.application.entities.Chapter_;

public class ChapterSpecification {
    public static Specification<Chapter> hasName(String name) {
        return (root, query, cb) -> cb.equal(root.get(Chapter_.INDEX), name);
    }
    
    public static Specification<Chapter> likeContent(String content) {
        return (root, query, cb) -> cb.like(root.get(Chapter_.NAME), "%" + content + "%");
    }
    
    public static Specification<Chapter> isDelete(boolean isDelete) {
        return (root, query, cb) -> cb.equal(root.get(Chapter_.IS_DELETE), isDelete);
    }
    
    public static Specification<Chapter> hasUpdateAtFrom(String updateAtFrom) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(Chapter_.UPDATE_AT), Common.stringToDate(updateAtFrom));
    }
    
    public static Specification<Chapter> hasUpdateAtTo(String updateAtTo) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(Chapter_.UPDATE_AT), Common.getLastOfTheDate(Common.stringToDate(updateAtTo)));
    }
}
