package com.flearndriving.management.application.respositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flearndriving.management.application.dto.AccountLogin;
import com.flearndriving.management.application.entities.Account;

@Repository
public interface AccountsRespository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

    @Query("SELECT a FROM Account a WHERE a.isDelete = false AND a.userName = :userName AND a.role.roleId = 1")
    Account findByUserNameAdmin(String userName);

    @Query("SELECT a FROM Account a WHERE a.isDelete = false AND a.email = :email AND a.role.roleId = 1")
    Account findByEmail(String email);

    @Query("SELECT count(a) > 0 FROM Account a WHERE a.isDelete = false AND a.userName = :userName AND a.role.roleId = 1")
    boolean existsByUserName(String userName);

    @Query("SELECT a FROM Account a WHERE a.isDelete = false AND a.accountId = :accountId ")
    public Account findByAccountId(Long accountId);

    @Query("SELECT count(a) FROM Account a WHERE a.role.roleId = 2 AND a.isDelete = false ")
    Integer countAccountUser();

    @Query("SELECT a FROM Account a WHERE a.role.roleId = 2 AND a.createAt >= :startDate AND a.createAt <= :endDate")
    List<Account> findReportByCreateDate(Date startDate, Date endDate);
    
    @Query(value="SELECT new com.flearndriving.management.application.dto.AccountLogin(a.accountId, "
            + "a.userName, "
            + "a.firstName, "
            + "a.middleName, "
            + "a.lastName, "
            + "a.email, "
            + "a.description) "
            + "FROM Account a "
            + "WHERE a.role.roleId = 1 "
            + "AND a.isDelete = false "
            + "AND a.userName = :userName")
    AccountLogin findBasicInfoByUserNameAdmin(String userName);
}
