package com.flearndriving.management.application.respositories;

import com.flearndriving.management.application.entities.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ExamRepository extends CrudRepository<Exam, Long>, JpaRepository<Exam, Long>,
        PagingAndSortingRepository<Exam, Long>, JpaSpecificationExecutor<Exam> {
}
