package com.razor.architectureexample.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int note_id;

    private String title;

    private String description;

    private String priority;

    public Note(String title, String description, String priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public int getNote_id() {
        return note_id;
    }

    public void setNote_id(int note_id) {
        this.note_id = note_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }
}

