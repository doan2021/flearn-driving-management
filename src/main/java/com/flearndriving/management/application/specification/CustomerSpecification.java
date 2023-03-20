package com.flearndriving.management.application.specification;

import com.flearndriving.management.application.entities.Customer;
import com.flearndriving.management.application.entities.Customer_;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecification {

    public static Specification<Customer> hasEmail(String email) {
        return (root, query, cb) -> cb.equal(root.get(Customer_.EMAIL), email);
    }

    public static Specification<Customer> notEqualUserName(String userName) {
        return (root, query, cb) -> cb.notEqual(root.get(Customer_.USER_NAME), userName);

    }

    public static Specification<Customer> hasUserName(String userName) {
        return (root, query, cb) -> cb.equal(root.get(Customer_.USER_NAME), userName);
    }

    public static Specification<Customer> likeFullName(String fullName) {
        return (root, query, cb) -> cb.like(
                cb.concat(cb.concat(root.get(Customer_.FIRST_NAME), " "), root.get(Customer_.LAST_NAME)),
                "%" + fullName + "%");
    }

    public static Specification<Customer> hasGender(Integer gender) {
        return (root, query, cb) -> cb.equal(root.get(Customer_.GENDER), gender);
    }

    public static Specification<Customer> isDelete(boolean isDelete) {
        return (root, query, cb) -> cb.equal(root.get(Customer_.IS_DELETE), isDelete);
    }
}
