package com.flearndriving.management.application.respositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.flearndriving.management.application.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

	Customer findByUserName(String userName);

	@Query("SELECT a FROM Customer a WHERE a.isDelete = false AND a.email = :email AND a.role.id = 1")
	Customer findByEmail(String email);

	Integer countByUserName(String userName);

	@Query("SELECT count(a) FROM Customer a WHERE a.role.id = 2 AND a.isDelete = false")
	Integer countCustomerUser();

	@Query("SELECT a FROM Customer a WHERE a.role.id = 2 AND a.createdDate >= :startDate AND a.createdDate <= :endDate")
	List<Customer> findReportByCreateDate(Date startDate, Date endDate);

	@Query("SELECT a FROM Customer a WHERE a.role.id = 1 AND a.isDelete = false AND a.userName = :userName")
	Customer findBasicInfoByUserNameAdmin(String userName);
}
