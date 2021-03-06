package com.flearndriving.management.application.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.flearndriving.management.application.entities.DrivingLicense;
import com.flearndriving.management.application.entities.ExamQuestions;
import com.flearndriving.management.application.entities.Question;

@Repository
public interface ExamQuestionsRepository extends JpaRepository<ExamQuestions, Long>,
        PagingAndSortingRepository<ExamQuestions, Long>, JpaSpecificationExecutor<ExamQuestions> {
    
    @Query("SELECT eq FROM ExamQuestions eq WHERE eq.examQuestionsId = :examQuestionsId AND eq.isDelete = false")
    public ExamQuestions findByExamQuestionsId(Long examQuestionsId);

    @Query("SELECT eq FROM ExamQuestions eq WHERE eq.drivingLicense = :drivingLicense AND eq.isDelete = false")
    public List<ExamQuestions> findByDrivingLicense(DrivingLicense drivingLicense);
    
    
    @Query(value="SELECT eqd.question"
            + " FROM ExamQuestionsDetail eqd"
            + " WHERE eqd.examQuestions.examQuestionsId = :examQuestionsId")
    public List<Question> findQuestionInExamQuestionsId(Long examQuestionsId);
}
