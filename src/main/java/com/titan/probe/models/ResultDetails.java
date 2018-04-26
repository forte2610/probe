package com.titan.probe.models;

public class ResultDetails {
    private String keyword;
    private int count;
    private int pages;
    private String timeElapsed;

    public ResultDetails() {
    }

    public ResultDetails(String keyword, int count, int pages, String timeElapsed) {
        this.keyword = keyword;
        this.count = count;
        this.pages = pages;
        this.timeElapsed = timeElapsed;
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
