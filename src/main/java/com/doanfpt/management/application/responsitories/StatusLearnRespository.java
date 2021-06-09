package com.doanfpt.management.application.responsitories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.doanfpt.management.application.entities.Account;
import com.doanfpt.management.application.entities.Chapter;
import com.doanfpt.management.application.entities.Question;
import com.doanfpt.management.application.entities.StatusLearn;

@Repository
public interface StatusLearnRespository extends JpaRepository<StatusLearn, Long> {
    public StatusLearn findByQuestionAndAccount(Question question, Account account);

    public List<StatusLearn> findByQuestionInAndAccount(List<Question> listQuestion, Account account);

    @Query("   SELECT  st.question "
         + "   FROM    StatusLearn st "
         + "   WHERE   st.account = :account "
         + "       AND st.question.chapter = :chapter")
    public List<Question> getListQuestionHadAnswer(@Param("account") Account account,
            @Param("chapter") Chapter chapter);
    
    @Query("   SELECT sl.question "
            + "FROM StatusLearn sl "
            + "WHERE sl.question.chapter = :chapter "
            + "AND sl.account = :account "
            + "AND sl.statusQuestion = :statusQuestion")
    public List<Question> getListQuestionWithStatus(Chapter chapter, Account account, Integer statusQuestion);
    
}
