package org.example.review;

import org.example.books.Book;

public class Review {
    private Integer id;
    private Book book;
    private Integer score;
    private String comment;


    public Review() {
    }

    public Review(Book book, Integer score, String comment) {
        this.book = book;
        this.score = score;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "ID: '" + id + '\'' +
                ", BOOK: '" + book +
                ", SCORE: '" + score + '\'' +
                ", COMMENT: '" + comment + '\'' +
                '}';
    }
}
