package com.stepienk.libraryapp.model.sql_available_books;

/**
 * Created by Krzysiek on 2015-04-14.
 * Book object for holding all data
 */
public class Book {

    private int id;
    private String name;
    private String description;
    private byte isTaken;
    private String date;
    private String link;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public byte getIsTaken() {
        return isTaken;
    }

    public void setIsTaken(byte isTaken) {
        this.isTaken = isTaken;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
