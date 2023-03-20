package com.flearndriving.management.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flearndriving.management.application.respositories.DrivingLicenseInfoRepository;

@Service

public class DrivingLicenseInfoServices {
	@Autowired
    DrivingLicenseInfoRepository drivingLicenseInfoRepository;

	public Integer countDrivingLicenseInfo() {
		return drivingLicenseInfoRepository.countDrivingLicenseInfo();
	}
}
