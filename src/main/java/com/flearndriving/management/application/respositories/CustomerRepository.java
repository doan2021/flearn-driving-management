package com.flearndriving.management.application.respositories;

import com.flearndriving.management.application.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    @Query("SELECT a FROM Customer a WHERE a.isDelete = false AND a.userName = :userName AND a.role.id = 1")
    Customer findByUserNameAdmin(String userName);

    @Query("SELECT a FROM Customer a WHERE a.isDelete = false AND a.email = :email AND a.role.id = 1")
    Customer findByEmail(String email);

    Integer countByUserName(String userName);

    @Query("SELECT count(a) FROM Customer a WHERE a.role.id = 2 AND a.isDelete = false")
    Integer countCustomerUser();

    @Query("SELECT a FROM Customer a WHERE a.role.id = 2 AND a.createAt >= :startDate AND a.createAt <= :endDate")
    List<Customer> findReportByCreateDate(Date startDate, Date endDate);
    
    @Query(value="SELECT a FROM Customer a WHERE a.role.id = 1 AND a.isDelete = false AND a.userName = :userName")
    Customer findBasicInfoByUserNameAdmin(String userName);
}
