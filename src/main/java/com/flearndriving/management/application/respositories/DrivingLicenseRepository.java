package com.flearndriving.management.application.respositories;

import com.flearndriving.management.application.entities.DrivingLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DrivingLicenseRepository extends JpaRepository<DrivingLicense, Long>, PagingAndSortingRepository<DrivingLicense, Long>,
        JpaSpecificationExecutor<DrivingLicense> {

    @Query("SELECT dl FROM DrivingLicense dl WHERE dl.id = :drivingLicenseId AND dl.isDelete = false")
    DrivingLicense findByDrivingLicenseId(Long drivingLicenseId);

}
