package com.flearndriving.management.application.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flearndriving.management.application.entities.Province;

@Repository
public interface ProvinceRespository extends JpaRepository<Province, Long>{
}
