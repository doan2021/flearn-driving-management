package com.doanfpt.management.application.dto;

public class NumberOfChapter {
    private Long chapterId;
    private String numberQuestionInChapter;

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

    public String getNumberQuestionInChapter() {
        return numberQuestionInChapter;
    }

    public void setNumberQuestionInChapter(String numberQuestionInChapter) {
        this.numberQuestionInChapter = numberQuestionInChapter;
    }

}
