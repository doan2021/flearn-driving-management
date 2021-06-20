package com.doanfpt.management.application.responsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.doanfpt.management.application.entities.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long>, PagingAndSortingRepository<Exam, Long>, JpaSpecificationExecutor<Exam>{

	public Exam findByExamIdAndIsDelete(Long examId, boolean isDelete);

	public List<Exam> findByIsDeleteAndIsTrial(boolean isDelete, boolean isTrail);
}
