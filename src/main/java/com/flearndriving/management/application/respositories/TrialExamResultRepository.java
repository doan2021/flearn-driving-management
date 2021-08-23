package com.flearndriving.management.application.respositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flearndriving.management.application.entities.TrialExamResult;

@Repository
public interface TrialExamResultRepository extends JpaRepository<TrialExamResult, Long>, JpaSpecificationExecutor<TrialExamResult> {

	@Query("SELECT ter FROM TrialExamResult ter WHERE ter.createAt >= :startDate AND ter.createAt <= :endDate")
	List<TrialExamResult> findReportByCreateDate(Date startDate, Date endDate);

}
