package com.flearndriving.management.application.respositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flearndriving.management.application.entities.Account;

@Repository
public interface AccountsRespository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

    public Account findByUserName(String userName);

    Account findByEmail(String email);

    Boolean existsByUserName(String userName);

    public Account findByAccountId(Long accountId);

    @Query("SELECT count(a) FROM Account a WHERE a.role.roleId = 2")
    Integer countAccount();

    @Query("SELECT a FROM Account a WHERE a.role.roleId = 2 AND a.createAt >= :startDate AND a.createAt <= :endDate")
    List<Account> findReportByCreateDate(Date startDate, Date endDate);
}
