package com.taskmanager;

import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private int id;
    private String title;
    private String description;
    private Date dueDate;
    private boolean isComplete;
    private String priority;

    public Task(int id, String title, String description, Date dueDate, boolean isComplete, String priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.isComplete = isComplete;
        this.priority = priority;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
    public boolean isComplete() { return isComplete; }
    public void setComplete(boolean complete) { isComplete = complete; }
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
}
