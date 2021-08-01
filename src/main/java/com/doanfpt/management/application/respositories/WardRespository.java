package com.doanfpt.management.application.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doanfpt.management.application.entities.Ward;

@Repository
public interface WardRespository extends JpaRepository<Ward, Long>{
}
