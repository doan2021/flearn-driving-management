package com.flearndriving.management.application.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flearndriving.management.application.entities.Document;

@Repository
public interface DocumentRespository  extends JpaRepository<Document, Long> {
    @Query("SELECT d.path "
            + "FROM Document d "
            + "WHERE d.type = :type "
            + "AND d.account.accountId = :accountId")
    String findUrlDocumentByTypeAndAccountId(Integer type, Long accountId);
    
    @Query("SELECT d "
            + "FROM Document d "
            + "WHERE d.type = :type "
            + "AND d.account.accountId = :accountId")
    Document findByTypeAndAccountId(Integer type, Long accountId);
    
    @Query("SELECT d.path"
            + " FROM Document d "
            + " WHERE d.question.questionId = :questionId ")
    List<String> getListUrlImageByQuestionId(Long questionId);
}
