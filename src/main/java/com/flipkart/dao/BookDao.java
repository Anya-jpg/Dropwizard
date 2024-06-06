//import com.flipkart.beans.Book;
//import com.flipkart.map.BookMap;
//import org.skife.jdbi.v2.sqlobject.Bind;
//import org.skife.jdbi.v2.sqlobject.BindBean;
//import org.skife.jdbi.v2.sqlobject.SqlQuery;
//import org.skife.jdbi.v2.sqlobject.SqlUpdate;
//import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
//
//import java.util.List;
//
//@RegisterMapper(BookMap.class)
//public interface BookDao {
//
//    @SqlUpdate("INSERT INTO Book (bookName, authorId) VALUES (:bookName, :authorId)")
//    void insertBook(@BindBean Book book);
//
//    @SqlQuery("SELECT * FROM Book")
//    List<Book> getAllBooks();
//
//    @SqlQuery("SELECT * FROM Book WHERE bookId = :bookId")
//    Book getBookByID(@Bind("bookId") int bookId);
//}



package com.flipkart.dao;

import com.flipkart.beans.Book;
import com.flipkart.map.BookMap;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(BookMap.class)
public interface BookDao {

    @SqlUpdate("INSERT INTO Book (bookName, authorId) VALUES (:bookName, :authorId)")
    void insertBook(@BindBean Book book);

    @SqlQuery("SELECT * FROM Book")
    List<Book> getAllBooks();

    @SqlQuery("SELECT * FROM Book WHERE bookId = :bookId")
    Book getBookByID(@Bind("bookId") int bookId);
}
