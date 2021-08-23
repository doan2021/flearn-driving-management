package com.flearndriving.management.application.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.flearndriving.management.application.entities.Question;

@Repository
public interface QuestionsRespository
        extends JpaRepository<Question, Long>, PagingAndSortingRepository<Question, Long> {

    @Query("SELECT count(q) > 0 FROM Question q WHERE q.isDelete = false AND q.number = :number")
    boolean existsByNumber(Integer number);

    @Query("SELECT q FROM Question q WHERE q.isDelete = false AND q.chapter.chapterId = :chapterId")
    List<Question> findQuestionByChapterId(Long chapterId);

    @Query("SELECT q FROM Question q WHERE q.isDelete = false AND q.questionId = :questionId")
    Question findByQuestionId(Long questionId);

    @Query("SELECT count(q) FROM Question q WHERE q.isDelete = false AND q.chapter.isDelete = false")
    Integer countQuestion();

    @Query("SELECT q FROM Question q WHERE q.isDelete = false AND q.chapter.isDelete = false AND isParalysis = true")
    List<Question> findQuestionParalysis();
}
