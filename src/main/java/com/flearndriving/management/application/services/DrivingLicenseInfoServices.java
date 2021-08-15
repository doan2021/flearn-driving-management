package com.flearndriving.management.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flearndriving.management.application.respositories.DrivingLicenseInfoRespository;

@Service

public class DrivingLicenseInfoServices {
	@Autowired
	DrivingLicenseInfoRespository drivingLicenseInfoRespository;

	public Integer countDrivingLicenseInfo() {
		return drivingLicenseInfoRespository.countDrivingLicenseInfo();
	}
}
