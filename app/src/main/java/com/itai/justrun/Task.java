package com.itai.justrun;

import java.io.Serializable;

public class Task implements Serializable {

    private String id;

    private String description;
    private long date;

    public Task (){};
    public Task(String id, String description, long date) {
        this.description = description;
        this.date = date;
        this.id =id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
