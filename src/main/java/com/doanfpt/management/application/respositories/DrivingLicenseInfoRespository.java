package com.doanfpt.management.application.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doanfpt.management.application.entities.ExamProfile;

@Repository
public interface DrivingLicenseInfoRespository extends JpaRepository<ExamProfile, Long> {
	@Query("SELECT count(d) FROM ExamProfile d WHERE d.status = 3")
	Integer countDrivingLicenseInfo();
}
