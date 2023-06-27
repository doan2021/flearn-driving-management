package com.flearndriving.management.application.specification;

import com.flearndriving.management.application.common.Common;
import com.flearndriving.management.application.entities.Chapter;
import com.flearndriving.management.application.entities.Chapter_;
import com.flearndriving.management.application.utils.DateTimeUtils;
import org.springframework.data.jpa.domain.Specification;

public class ChapterSpecification {
    public static Specification<Chapter> hasIndex(String name) {
        return (root, query, cb) -> cb.equal(root.get(Chapter_.NAME), name);
    }

    public static Specification<Chapter> isDelete(boolean isDelete) {
        return (root, query, cb) -> cb.equal(root.get(Chapter_.IS_DELETE), isDelete);
    }

    public static Specification<Chapter> likeContent(String content) {
        return (root, query, cb) -> cb.like(root.get(Chapter_.CONTENT), "%" + content + "%");
    }

    public static Specification<Chapter> hasUpdateAtFrom(String updateAtFrom) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(Chapter_.LAST_MODIFIED_DATE), Common.stringToDate(updateAtFrom));
    }

    public static Specification<Chapter> hasUpdateAtTo(String updateAtTo) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(Chapter_.LAST_MODIFIED_DATE),
                DateTimeUtils.atEndOfDay(Common.stringToDate(updateAtTo)));
    }
}
