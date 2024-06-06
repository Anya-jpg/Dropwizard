// Book.java
package com.flipkart.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Book {


    @JsonProperty
    private Integer bookId;

    @JsonProperty
    private String bookName;

    @JsonProperty
    private Integer authorId;

    public Book(int bookId, String bookName, int authorId) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.authorId = authorId;
    }



    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
}
