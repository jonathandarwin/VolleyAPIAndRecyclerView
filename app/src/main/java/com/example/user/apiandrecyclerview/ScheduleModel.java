package com.example.user.apiandrecyclerview;

/**
 * Created by User on 12/4/2018.
 */

public class ScheduleModel {
    public String Title;
    public String Description;
    public String Author;

    public ScheduleModel(){

    }

    public ScheduleModel(String title, String description, String author) {
        Title = title;
        Description = description;
        Author = author;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }
}
