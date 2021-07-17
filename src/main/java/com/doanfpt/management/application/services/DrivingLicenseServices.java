package com.doanfpt.management.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doanfpt.management.application.entities.DrivingLicense;
import com.doanfpt.management.application.responsitories.DrivingLicenseRespository;

@Service
public class DrivingLicenseServices {

    @Autowired
    private DrivingLicenseRespository drivingLicenseRespository;

    public List<DrivingLicense> findAll() {
        return drivingLicenseRespository.findAll();
    }

    public DrivingLicense findById(Long drivingLicenseId) {
        return drivingLicenseRespository.getOne(drivingLicenseId);
    }
}
