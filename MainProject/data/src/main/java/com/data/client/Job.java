package com.data.client;

public class Job {

    private String title;


    public Job(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Job{" +
                "title='" + title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String tittel) {
        this.title = tittel;
    }
}
