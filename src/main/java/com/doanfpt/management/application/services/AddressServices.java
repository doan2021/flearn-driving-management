package com.doanfpt.management.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doanfpt.management.application.entities.Province;
import com.doanfpt.management.application.respositories.ProvinceRespository;

@Service
public class AddressServices {

    @Autowired
    ProvinceRespository provinceRespository;

    public List<Province> getAllProvince() {
        return provinceRespository.findAll();
    }
}
