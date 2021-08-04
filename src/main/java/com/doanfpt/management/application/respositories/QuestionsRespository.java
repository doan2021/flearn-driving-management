package com.doanfpt.management.application.respositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.doanfpt.management.application.entities.Account;
import com.doanfpt.management.application.entities.Chapter;
import com.doanfpt.management.application.entities.Question;

@Repository
public interface QuestionsRespository  extends JpaRepository<Question, Long>, PagingAndSortingRepository<Question, Long> {

    public Question findByNumberAndIsDelete(Integer number, Boolean isDelete);
    
    public Page<Question> findByChapter(Chapter chapter, Pageable pageable);
    
    public List<Question> findByQuestionIdNotInAndChapter(List<Long> listIds, Chapter chapter);
    
    public List<Question> findByQuestionIdIn(List<Long> listIds);

    @Query("SELECT q "
            + "FROM Question q "
            + "WHERE q.chapter = :chapter "
            + "    AND q NOT IN (SELECT sl.question "
            + "                  FROM StatusLearn sl "
            + "                  WHERE sl.account = :account "
            + "                      AND (sl.statusQuestion = 2 or sl.statusQuestion = 3))")
    public List<Question> getListQuestionRest(Chapter chapter, Account account);
   
    @Query("SELECT count(q) FROM Question q WHERE q.isDelete = false")
	Integer countQuestion();
}
