package com.flipkart.dao;

import com.flipkart.beans.Author;
import com.flipkart.beans.Book;
import com.flipkart.map.AuthorMap;
import com.flipkart.map.BookMap;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(AuthorMap.class)
public interface AuthorDao {

    @SqlUpdate("INSERT INTO Author (authorName) VALUES (:authorName)")
    void addAuthorById(@BindBean Author author);

    @SqlQuery("SELECT * FROM Author")
    List<Author> getAllAuthors();

    @SqlQuery("SELECT * FROM Author WHERE authorId = :authorId")
    Author getAuthorByID(@Bind("authorId") int authorId);

    @RegisterMapper(BookMap.class)
    @SqlQuery("SELECT * FROM Book WHERE authorId = :authorId")
    List<Book> getAuthorBooks(@Bind("authorId") int authorId);

    @SqlQuery("SELECT last_insert_id()")
    int lastInsertId();
}
