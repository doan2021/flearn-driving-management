package com.doanfpt.management.application.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.doanfpt.management.application.common.Constant;
import com.doanfpt.management.application.dto.AnswerForm;
import com.doanfpt.management.application.dto.QuestionForm;
import com.doanfpt.management.application.entities.Answer;
import com.doanfpt.management.application.entities.Chapter;
import com.doanfpt.management.application.entities.Image;
import com.doanfpt.management.application.entities.Question;
import com.doanfpt.management.application.responsitories.ChapterResponsitory;
import com.doanfpt.management.application.responsitories.QuestionsRespository;

@Service
public class QuestionServices {

    @Autowired
    private QuestionsRespository questionsRespository;

    @Autowired
    private Environment env;

    @Autowired
    ChapterResponsitory chapterResponsitory;

    @Transactional
    public void createNewQuestion(QuestionForm form) {
        Chapter chapter = new Chapter();
        chapter = chapterResponsitory.getOne(form.getChapterId());
        Question question = new Question();
        question.setContent(form.getContent());
        question.setNumber(form.getNumber());
        question.setListImage(handleImage(question, form.getImages()));
        question.setChapter(chapter);
        List<Answer> listAnswers = new ArrayList<>();
        for (AnswerForm answer : form.getListAnswers()) {
            Answer ans = new Answer();
            ans.setContent(answer.getContent());
            ans.setTrue(answer.isTrue());
            ans.setQuestion(question);
            listAnswers.add(ans);
        }
        question.setListAnswers(listAnswers);
        questionsRespository.save(question);
    }

    public List<Image> handleImage(Question question, MultipartFile[] data) {
        List<Image> images = new ArrayList<>();
        for (MultipartFile c : data) {
            if (!c.isEmpty()) {
                Image image = new Image();
                image.setFileName(c.getOriginalFilename());
                image.setUrl(writeFile(c));
                image.setDescription("Create new question");
                image.setQuestion(question);
                images.add(image);
            }
        }
        return images;
    }

    public String writeFile(MultipartFile fileImage) {
        byte data[];
        String pathClassPath = env.getProperty("url-class-path");
        String pathFolderUpload = env.getProperty("url-upload-folder");
        String fileName = fileImage.getOriginalFilename();
        try {
            data = fileImage.getBytes();
            File file = new File(pathClassPath + pathFolderUpload + "/" + fileName);
            FileOutputStream out = new FileOutputStream(file);
            out.write(data);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pathFolderUpload + "/" + fileName;
    }

    public List<Question> getAllQuestions() {
        return questionsRespository.findAll();
    }

    public Page<Question> getQuestionInChapter(Long chapterId, Integer pageNumber) {
        if (pageNumber == null) {
            pageNumber = 0;
        }
        Pageable pageable = PageRequest.of(pageNumber, Constant.RECORD_PER_PAGE);
        Chapter chapter = chapterResponsitory.findByChapterIdAndIsDelete(chapterId, false);
        return questionsRespository.findByChapter(chapter, pageable);
    }
}
