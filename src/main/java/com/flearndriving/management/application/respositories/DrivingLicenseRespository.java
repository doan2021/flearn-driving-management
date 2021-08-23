package com.flearndriving.management.application.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.flearndriving.management.application.entities.DrivingLicense;

@Repository
public interface DrivingLicenseRespository extends JpaRepository<DrivingLicense, Long>, PagingAndSortingRepository<DrivingLicense, Long>, JpaSpecificationExecutor<DrivingLicense> {

    @Query("SELECT dl FROM DrivingLicense dl WHERE dl.drivingLicenseId = :drivingLicenseId AND dl.isDelete = false")
	public DrivingLicense findByDrivingLicenseId(Long drivingLicenseId);

}
