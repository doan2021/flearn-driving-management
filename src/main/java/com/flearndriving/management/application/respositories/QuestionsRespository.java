package com.flearndriving.management.application.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.flearndriving.management.application.entities.Chapter;
import com.flearndriving.management.application.entities.Question;

@Repository
public interface QuestionsRespository
        extends JpaRepository<Question, Long>, PagingAndSortingRepository<Question, Long> {

    public boolean existsByNumber(Integer number);

    public List<Question> findByChapter(Chapter chapter);

    public Question findByQuestionId(Long questionId);

    @Query("SELECT count(q) FROM Question q")
    Integer countQuestion();
}
