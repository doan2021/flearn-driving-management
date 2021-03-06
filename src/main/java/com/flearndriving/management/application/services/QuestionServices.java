package com.flearndriving.management.application.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.flearndriving.management.application.common.Common;
import com.flearndriving.management.application.common.Constant;
import com.flearndriving.management.application.common.MimeTypes;
import com.flearndriving.management.application.dto.AnswerForm;
import com.flearndriving.management.application.dto.QuestionForm;
import com.flearndriving.management.application.entities.Answer;
import com.flearndriving.management.application.entities.Chapter;
import com.flearndriving.management.application.entities.Document;
import com.flearndriving.management.application.entities.Question;
import com.flearndriving.management.application.exception.BusinessException;
import com.flearndriving.management.application.respositories.ChapterRespository;
import com.flearndriving.management.application.respositories.DocumentRespository;
import com.flearndriving.management.application.respositories.QuestionsRespository;

@Service
public class QuestionServices {

    @Autowired
    QuestionsRespository questionsRespository;

    @Autowired
    ChapterRespository chapterResponsitory;
    
    @Autowired
    DocumentRespository documentRespository;
    
    @Autowired
    AmazonS3ClientService amazonS3ClientService;

    @Transactional
    public void createQuestion(QuestionForm questionForm) {
        if (questionForm == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Dữ liệu truyền lên không đúng!");
        }
        Chapter chapter = chapterResponsitory.findByChapterId(questionForm.getChapterId());
        if (chapter == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Chương không tồn tại!");
        }
        Question question = new Question();
        question.setContent(questionForm.getContent());
        question.setNumber(questionForm.getNumber());
        question.setChapter(chapter);
        question.setParalysis(questionForm.getIsParalysis());
        List<Answer> listAnswers = new ArrayList<>();
        for (AnswerForm answer : questionForm.getListAnswers()) {
            Answer ans = new Answer();
            ans.setContent(answer.getContent());
            ans.setTrue(answer.isTrue());
            ans.setQuestion(question);
            ans.setCreateBy(Common.getUsernameLogin());
            ans.setCreateAt(Common.getSystemDate());
            ans.setUpdateBy(Common.getUsernameLogin());
            ans.setUpdateAt(Common.getSystemDate());
            listAnswers.add(ans);
        }
        question.setListAnswers(listAnswers);
        question.setCreateBy(Common.getUsernameLogin());
        question.setCreateAt(Common.getSystemDate());
        question.setUpdateBy(Common.getUsernameLogin());
        question.setUpdateAt(Common.getSystemDate());

        // Handle document
        List<Document> listDocuments = new ArrayList<>();
        // Upload file CMND mặt trước
        if (questionForm.getImages() != null && !questionForm.getImages()[0].isEmpty()) {
            // Get file to client
            for(MultipartFile multipartFile : questionForm.getImages()) {
                Document document = new Document();
                document.setFileName(Common.generateFileName(multipartFile, Constant.DOCUMENT_QUESTION_IMAGE));
                document.setQuestion(question);
                document.setOriginFileName(multipartFile.getOriginalFilename());
                document.setExtension(MimeTypes.lookupExt(multipartFile.getContentType()));
                document.setContentType(multipartFile.getContentType());
                document.setSize(multipartFile.getSize());
                document.setType(Constant.TYPE_DOCUMENT_IDENTITY_CARD_IMAGE_FRONT);
                document.setDescription("Ảnh mô tả câu hỏi");
                document.setCreateAt(Common.getSystemDate());
                document.setCreateBy(Common.getUsernameLogin());
                document.setPath(amazonS3ClientService.uploadFileToS3Bucket(multipartFile, document.getFileName()));
                listDocuments.add(document);
            }
            question.setListImage(listDocuments);
        }
        questionsRespository.save(question);
    }

    public List<Question> getQuestionInChapter(Long chapterId) {
        return questionsRespository.findQuestionByChapterId(chapterId);
    }

    public Question getOneQuestion(Long questionId) {
        return questionsRespository.getOne(questionId);
    }
    
    public Integer countQuestion() {
        return questionsRespository.countQuestion();
    }

    public Object getQuestionDetail(Long questionId) {
        return questionsRespository.findByQuestionId(questionId);
    }

    public void deleteQuestion(Long questionId) {
        Question question = questionsRespository.findByQuestionId(questionId);
        if (question == null) {
            throw new BusinessException(Constant.HTTPS_STATUS_CODE_500, "Câu hỏi không tồn tại!");
        }
        question.setDelete(true);
        question.setUpdateAt(Common.getSystemDate());
        question.setUpdateBy(Common.getUsernameLogin());
        questionsRespository.save(question);
    }
}
