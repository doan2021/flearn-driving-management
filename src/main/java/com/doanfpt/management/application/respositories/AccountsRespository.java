package com.doanfpt.management.application.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doanfpt.management.application.entities.Account;

@Repository
public interface AccountsRespository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

	public Account findByUserNameAndIsDelete(String userName, boolean isDelete);

	Account findByEmail(String email);

	Boolean existsByUserName(String userName);

	public Account findByAccountIdAndIsDelete(Long accountId, boolean isNotDelete);

	@Query("SELECT count(a) FROM Account a WHERE a.isDelete = false and a.role.roleId = 2")
	Integer countAccount();

}
