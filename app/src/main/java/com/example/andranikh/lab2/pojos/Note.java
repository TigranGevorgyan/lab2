package com.example.andranikh.lab2.pojos;

import android.graphics.Color;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by andranikh on 4/29/17.
 */

public class Note implements Serializable {
    static final long serialVersionUID = -1;

    private long id;
    private String title;
    private String description;
    private Date createDate;
    private int color;
    private boolean important;

    public Note(String title, String description, int color, boolean important) {
        this.title = title;
        this.description = description;
        this.color = color;
        this.important = important;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", color=" + color +
                ", important=" + important +
                '}';
    }

    public Note(long id, String title, int color, boolean important, Date createDate, String description) {
        this.id = id;
        this.title = title;
        this.color = color;
        this.important = important;
        this.createDate = createDate;
        this.description = description;
    }

    public Note() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
