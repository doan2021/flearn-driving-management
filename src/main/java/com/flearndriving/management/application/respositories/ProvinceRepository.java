package com.flearndriving.management.application.respositories;

import com.flearndriving.management.application.entities.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province, Long> {
}
