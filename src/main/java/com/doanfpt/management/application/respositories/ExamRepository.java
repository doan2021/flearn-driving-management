package com.doanfpt.management.application.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.doanfpt.management.application.entities.Exam;

public interface ExamRepository extends CrudRepository<Exam, Long>, JpaRepository<Exam, Long>, PagingAndSortingRepository<Exam, Long>, JpaSpecificationExecutor<Exam>{

	public Exam findByExamIdAndIsDelete(Long examId, boolean isDelete);
	public Boolean existsByExamIdAndIsDelete(Long examId, boolean isDelete);
}
