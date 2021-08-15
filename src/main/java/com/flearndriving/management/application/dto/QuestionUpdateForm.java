package com.flearndriving.management.application.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class QuestionUpdateForm {
    private Integer number;
    private String content;
    private Boolean isParalysis;
    private List<AnswerForm> listAnswers;
    private MultipartFile[] images;
    private Long chapterId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<AnswerForm> getListAnswers() {
        return listAnswers;
    }

    public void setListAnswers(List<AnswerForm> listAnswers) {
        this.listAnswers = listAnswers;
    }

    public MultipartFile[] getImages() {
        return images;
    }

    public void setImages(MultipartFile[] images) {
        this.images = images;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public Boolean getIsParalysis() {
        return isParalysis;
    }

    public void setIsParalysis(Boolean isParalysis) {
        this.isParalysis = isParalysis;
    }

}
