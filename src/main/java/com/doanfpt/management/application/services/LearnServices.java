package com.doanfpt.management.application.services;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.entities.Account;
import com.doanfpt.management.application.entities.Answer;
import com.doanfpt.management.application.entities.Chapter;
import com.doanfpt.management.application.entities.HistoryAnswer;
import com.doanfpt.management.application.entities.Question;
import com.doanfpt.management.application.entities.StatusLearn;
import com.doanfpt.management.application.respone.ResponeData;
import com.doanfpt.management.application.respositories.AccountsRespository;
import com.doanfpt.management.application.respositories.AnswerRespository;
import com.doanfpt.management.application.respositories.ChapterRespository;
import com.doanfpt.management.application.respositories.HistoryAnswerRespository;
import com.doanfpt.management.application.respositories.QuestionsRespository;
import com.doanfpt.management.application.respositories.StatusLearnRespository;

@Service
public class LearnServices {
    
    @Autowired
    ChapterRespository chapterResponsitory;
    
    @Autowired
    QuestionsRespository questionsRespository;
    
    @Autowired
    AnswerRespository answerRespository;
    
    @Autowired
    AccountsRespository accountRespository;
    
    @Autowired
    HistoryAnswerRespository historyAnswerRespository;
    
    @Autowired
    AccountServices accountsServices;
    
    @Autowired
    StatusLearnRespository statusLearnRespository;

    public ResponeData getQuestionInChapter(Long chapterId) {
        // Init respone data
        ResponeData responeData = new ResponeData();
        // Get user login
        Account account = accountsServices.getAccountLogin();
        // Get chapter
        Chapter chapter = chapterResponsitory.getOne(chapterId);
        // Get list question in chapter
        Random rand = new Random();
        Question questionRandom = null;
        int rest = 0;
        int familiar = 0;
        int knowledge = 0;
        List<Question> listQuestionRest = questionsRespository.getListQuestionRest(chapter, account);
        List<Question> listQuestionFamiliar = statusLearnRespository.getListQuestionWithStatus(chapter, account, 2);
        List<Question> listQuestionKnowledge = statusLearnRespository.getListQuestionWithStatus(chapter, account, 3);
        if (listQuestionRest != null && listQuestionRest.size() != 0) {
            questionRandom = listQuestionRest.get(rand.nextInt(listQuestionRest.size()));
        } else if (listQuestionFamiliar != null && listQuestionFamiliar.size() != 0) {
                questionRandom = listQuestionFamiliar.get(rand.nextInt(listQuestionFamiliar.size()));
        } else {
            questionRandom = listQuestionKnowledge.get(rand.nextInt(listQuestionKnowledge.size()));
        }
        
        if (listQuestionRest != null && listQuestionRest.size() != 0) {
            rest = listQuestionRest.size();
        }
        
        if (listQuestionFamiliar != null && listQuestionFamiliar.size() != 0) {
            familiar = listQuestionFamiliar.size();
        }
        
        if (listQuestionKnowledge != null && listQuestionKnowledge.size() != 0) {
            knowledge = listQuestionKnowledge.size();
        }
        
        // Get list status learn
        responeData.putResult("rest", rest);
        responeData.putResult("familiar", familiar);
        responeData.putResult("knowledge", knowledge);
        responeData.putResult("questionRandom", questionRandom);
        responeData.putResult("chapter", chapter);
        return responeData;
    }
    
    @Transactional
    public ResponeData checkResultAnswer(Long questionId, Long answerId) {
        ResponeData responeData = new ResponeData();
        Question question = new Question();
        question = questionsRespository.getOne(questionId);
        Answer answer = new Answer();
        answer = answerRespository.getOne(answerId);
        if (answer.isTrue()) {
            responeData.setStatus(Constant.STR_TRUE);
            responeData.setMessage("Correct !!");
        } else {
            responeData.setStatus(Constant.STR_FALSE);
            responeData.setMessage("Incorrect !!");
        }
        setHistoryAnswer(question, answer);
        return responeData;
    }
    
    public void setHistoryAnswer(Question question, Answer answer) {
        Account account = accountsServices.getAccountLogin();
        // Set status learn
        StatusLearn statusLearn = statusLearnRespository.findByQuestionAndAccount(question, account);
        if (statusLearn == null) {
            statusLearn = new StatusLearn();
            statusLearn.setQuestion(question);
            statusLearn.setAccount(account);
            if (answer.isTrue()) {
                statusLearn.setCorrectNumberOfTimes(1);
                statusLearn.setIncorrectNumberOfTimes(0);
                statusLearn.setStatusQuestion(2);
            } else {
                statusLearn.setCorrectNumberOfTimes(0);
                statusLearn.setIncorrectNumberOfTimes(1);
                statusLearn.setStatusQuestion(1);
            }
        } else {
            List<Boolean> statusLastQuestion = historyAnswerRespository.checkLastStatusQuestion(question, account);
            if (answer.isTrue()) {
                if (statusLastQuestion.get(0)) {
                    statusLearn.setStatusQuestion(3);
                }
                statusLearn.setCorrectNumberOfTimes(statusLearn.getCorrectNumberOfTimes() + 1);
               } else {
                if (statusLearn.getCorrectNumberOfTimes() > 0) {
                    statusLearn.setStatusQuestion(2);
                } else {
                    statusLearn.setStatusQuestion(1);
                }
                statusLearn.setIncorrectNumberOfTimes(statusLearn.getIncorrectNumberOfTimes() + 1);
            }
        }
        statusLearn.setUpdateAt(new Date());
        statusLearnRespository.save(statusLearn);
        HistoryAnswer historyAnswer = new HistoryAnswer();
        historyAnswer.setCorrect(answer.isTrue());
        historyAnswer.setDateAnswer(new Date());
        historyAnswer.setNote("Answer when learn");
        historyAnswer.setAnswer(answer);
        historyAnswer.setAccount(account);
        historyAnswerRespository.save(historyAnswer);
    }
}
