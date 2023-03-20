package com.flearndriving.management.application.respositories;

import com.flearndriving.management.application.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long>{
}
