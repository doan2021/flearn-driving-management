package com.doanfpt.management.application.specification;

import org.springframework.data.jpa.domain.Specification;

import com.doanfpt.management.application.entities.Account;
import com.doanfpt.management.application.entities.Account_;

public class AccountSpecification {
    public static Specification<Account> hasEmail(String email) {
        return (root, query, cb) -> cb.equal(root.get(Account_.EMAIL), email);
    }
    public static Specification<Account> hasUserName(String userName) {
        return (root, query, cb) -> cb.equal(root.get(Account_.USER_NAME), userName);
    }
    
    public static Specification<Account> likeFullName(String fullName) {
        return (root, query, cb) -> cb.like(cb.concat(cb.concat(root.get(Account_.FIRST_NAME), " "), root.get(Account_.LAST_NAME)), "%" + fullName + "%");
    } 

	public static Specification<Account> isDelete(boolean isDelete) {
        return (root, query, cb) -> cb.equal(root.get(Account_.IS_DELETE), isDelete);
    }
	
	public static Specification<Account> hasGender(Integer gender) {
        return (root, query, cb) -> cb.equal(root.get(Account_.GENDER), gender);
    }
}
