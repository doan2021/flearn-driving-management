package com.doanfpt.management.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doanfpt.management.application.entities.Account;
import com.doanfpt.management.application.entities.Role;

@Repository
public interface AccountsRespository extends JpaRepository<Account, Long> {

    public Account findByUserNameAndIsDeleteAndRole(String userName, boolean isDelete, Role role);

    Account findByEmail(String email);

    Boolean existsByUserName(String userName);
}
