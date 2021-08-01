package com.doanfpt.management.application.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doanfpt.management.application.entities.DrivingLicense;

@Repository
public interface DrivingLicenseRespository extends JpaRepository<DrivingLicense, Long>{
}