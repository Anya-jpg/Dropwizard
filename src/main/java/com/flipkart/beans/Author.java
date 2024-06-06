// Author.java
package com.flipkart.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Author {

    @JsonProperty
    private Integer authorId;
    @JsonProperty
    private String authorName;

    public Author(String authorName, Integer authorId) {
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
