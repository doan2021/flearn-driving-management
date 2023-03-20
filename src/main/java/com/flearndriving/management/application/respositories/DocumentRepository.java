package com.flearndriving.management.application.respositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.flearndriving.management.application.entities.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Query("SELECT d.path "
            + "FROM Document d "
            + "WHERE d.type = :type "
            + "AND d.customer.id = :customerId")
    String findUrlDocumentByTypeAndCustomerId(Integer type, Long customerId);
    
    @Query("SELECT d "
            + "FROM Document d "
            + "WHERE d.type = :type "
            + "AND d.customer.id = :customerId")
    Document findByTypeAndCustomerId(Integer type, Long customerId);
    
    @Query("SELECT d.path"
            + " FROM Document d "
            + " WHERE d.question.id = :questionId")
    List<String> getListUrlImageByQuestionId(Long questionId);
}
