package org.example.book;

import org.example.author.Author;

public class Book {

    private Integer id;
    private String title;
    private Author author;
    private String description;



    public Book () {

    }

    public Book(String title, Author author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }


    @Override
    public String toString() {
        return "Book{" +
                ", ID: '" + id + '\'' +
                ", TITLE: '" + title + '\'' +
                ", DESCRIPTION: '" + description + '\'' +
                ", AUTHOR: '" + author +
                '}';
    }
}
