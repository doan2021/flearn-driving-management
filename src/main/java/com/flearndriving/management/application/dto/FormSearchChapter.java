package com.flearndriving.management.application.dto;

public class FormSearchChapter {

    private String index;
    private String name;
    private String updateAtFrom;
    private String updateAtTo;
    private Integer pageNumber;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdateAtFrom() {
        return updateAtFrom;
    }

    public void setUpdateAtFrom(String updateAtFrom) {
        this.updateAtFrom = updateAtFrom;
    }

    public String getUpdateAtTo() {
        return updateAtTo;
    }

    public void setUpdateAtTo(String updateAtTo) {
        this.updateAtTo = updateAtTo;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

}
