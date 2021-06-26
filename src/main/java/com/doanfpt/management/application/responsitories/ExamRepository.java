package com.doanfpt.management.application.responsitories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doanfpt.management.application.entities.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long>{

}
