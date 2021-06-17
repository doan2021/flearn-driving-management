package com.doanfpt.management.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.doanfpt.management.application.entities.Account;

@Repository
public interface AccountsRespository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

    public Account findByUserName(String userName);

    Account findByEmail(String email);

    Boolean existsByUserName(String userName);
}
