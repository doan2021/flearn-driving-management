package com.doanfpt.management.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.doanfpt.management.application.responsitories.DrivingLicenseInfoRespository;

@Service

public class DrivingLicenseInfoServices {
	@Autowired
	DrivingLicenseInfoRespository drivingLicenseInfoRespository;

	public Integer countDrivingLicenseInfo() {
		return drivingLicenseInfoRespository.countDrivingLicenseInfo();
	}
}
