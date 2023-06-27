package com.flearndriving.management.application.respositories;

import com.flearndriving.management.application.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface QuestionsRepository
        extends JpaRepository<Question, Long>, PagingAndSortingRepository<Question, Long> {

    @Query("SELECT count(q) FROM Question q WHERE q.isDelete = false AND q.number = :number")
    Integer countByNumber(Integer number);

    @Query("SELECT q FROM Question q WHERE q.isDelete = false AND q.chapter.id = :id")
    List<Question> findQuestionByChapterId(Long id);

    @Query("SELECT q FROM Question q WHERE q.isDelete = false AND q.id = :id")
    Question findByQuestionId(Long id);

    @Query("SELECT count(q) FROM Question q WHERE q.isDelete = false AND q.chapter.isDelete = false")
    Integer countQuestion();

    @Query("SELECT q FROM Question q WHERE q.isDelete = false AND q.chapter.isDelete = false AND q.isParalysis = true")
    List<Question> findQuestionParalysis();
}
