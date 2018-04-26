package com.titan.probe.models;

import java.util.List;

public class ResultObject {
    private List<Product> resultList;
    private String keyword;
    private int count;
    private int pages;
    private String timeElapsed;

    public ResultObject() {
    }

    public ResultObject(List<Product> resultList, String keyword, int count, int pages, String timeElapsed) {
        this.resultList = resultList;
        this.keyword = keyword;
        this.count = count;
        this.pages = pages;
        this.timeElapsed = timeElapsed;
    }

    public List<Product> getResultList() {
        return resultList;
    }

    public void setResultList(List<Product> resultList) {
        this.resultList = resultList;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(String timeElapsed) {
        this.timeElapsed = timeElapsed;
    }
}
