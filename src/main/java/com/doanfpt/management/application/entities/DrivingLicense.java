package com.doanfpt.management.application.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "driving_license")
public class DrivingLicense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driving_license_id")
    private Long drivingLicenseId;

    @Column(name = "name")
    private String name;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "number_year_expires")
    private Integer numberYearExpires;

    public Long getDrivingLicenseId() {
        return drivingLicenseId;
    }

    public void setDrivingLicenseId(Long drivingLicenseId) {
        this.drivingLicenseId = drivingLicenseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumberYearExpires() {
        return numberYearExpires;
    }

    public void setNumberYearExpires(Integer numberYearExpires) {
        this.numberYearExpires = numberYearExpires;
    }

}
