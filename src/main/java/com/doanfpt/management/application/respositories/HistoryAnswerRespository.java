package com.doanfpt.management.application.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.doanfpt.management.application.entities.Account;
import com.doanfpt.management.application.entities.HistoryAnswer;
import com.doanfpt.management.application.entities.Question;

@Repository
public interface HistoryAnswerRespository extends JpaRepository<HistoryAnswer, Long> {
    
    
    @Query(value = "SELECT ha.answer.isTrue "
            + "FROM HistoryAnswer ha "
            + "WHERE ha.account = :account "
            + "AND ha.answer.question = :question "
            + "ORDER BY ha.dateAnswer DESC ")
    public List<Boolean> checkLastStatusQuestion(Question question, Account account);
}
