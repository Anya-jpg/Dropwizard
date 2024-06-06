//// AuthorMapper.java
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
//public class AuthorMap implements ResultSetMapper<Author> {
//    private static final String AuthorID = "authorId";
//    private static final String AuthorNAME = "authorName";
//
//    public Author map(int i, ResultSet resultSet, StatementContext statementContext)
//            throws SQLException {
//        Author author = new Author(resultSet.getString(AuthorNAME),resultSet.getInt(AuthorID));
//        return author;
//    }
//}


package com.flipkart.map;

import com.flipkart.beans.Author;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMap implements ResultSetMapper<Author> {
    @Override
    public Author map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Author(
                r.getString("authorName"),
                r.getInt("authorId")
        );
    }
}
