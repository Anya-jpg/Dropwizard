//
//package com.flipkart.map;
//
//import com.flipkart.beans.Author;
//import com.flipkart.beans.Book;
//import org.skife.jdbi.v2.StatementContext;
//import org.skife.jdbi.v2.tweak.ResultSetMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class BookMap implements ResultSetMapper<Book> {
//    private static final String bookID = "bookId";
//    private static final String bookNAME = "AuthorName";
//    private static final String authorID = "authorId";
//
//    public Book map(int i, ResultSet resultSet, StatementContext statementContext)
//            throws SQLException {
//        Book book = new Book(resultSet.getInt(bookID),resultSet.getString(bookNAME),resultSet.getInt(authorID));
////        author.setAuthorId(resultSet.getInt(ID));
//        return book;
//    }
//}


package com.flipkart.map;

import com.flipkart.beans.Book;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMap implements ResultSetMapper<Book> {
    @Override
    public Book map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Book(
                r.getInt("bookId"),
                r.getString("bookName"),
                r.getInt("authorId")
        );
    }
}
