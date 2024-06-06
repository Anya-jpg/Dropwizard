package com.flipkart.service;

import com.flipkart.beans.Author;
import com.flipkart.beans.Book;
import com.flipkart.dao.AuthorDao;
import com.flipkart.dao.BookDao;
import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException;
import org.skife.jdbi.v2.exceptions.UnableToObtainConnectionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;

public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private static final String DATABASE_ACCESS_ERROR =
            "Could not reach the MySQL database. The database may be down or there may be network connectivity issues. Details: ";
    private static final String DATABASE_CONNECTION_ERROR =
            "Could not create a connection to the MySQL database. The database configurations are likely incorrect. Details: ";
    private static final String UNEXPECTED_DATABASE_ERROR =
            "Unexpected error occurred while attempting to reach the database. Details: ";
    private static final String SUCCESS = "Success";
    private static final String UNEXPECTED_DELETE_ERROR = "An unexpected error occurred while deleting employee.";
    private static final String EMPLOYEE_NOT_FOUND = "Employee id %s not found.";

    private final AuthorDao authorDao;
    private final BookDao bookDao;

    public UserService(AuthorDao authorDao, BookDao bookDao) {
        this.authorDao = authorDao;
        this.bookDao = bookDao;
    }

    public List<Author> getAllAuthors() {
        return authorDao.getAllAuthors();
    }

    public Author getAuthorById(int authorId) {
        Author author = authorDao.getAuthorByID(authorId);
        if (Objects.isNull(author)) {
            throw new WebApplicationException(String.format(EMPLOYEE_NOT_FOUND, authorId), Response.Status.NOT_FOUND);
        }
        return author;
    }

    public List<Book> getAuthorBooks(int authorId) {
        return authorDao.getAuthorBooks(authorId);
    }

    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    public Author addAuthorById(Author author) {
        authorDao.addAuthorById(author);
        return authorDao.getAuthorByID(authorDao.lastInsertId());
    }

    public void addBookById(Book book) {
        bookDao.insertBook(book);
    }

    public String performHealthCheck() {
        try {
            authorDao.getAllAuthors();
        } catch (UnableToObtainConnectionException ex) {
            return checkUnableToObtainConnectionException(ex);
        } catch (UnableToExecuteStatementException ex) {
            return checkUnableToExecuteStatementException(ex);
        } catch (Exception ex) {
            return UNEXPECTED_DATABASE_ERROR + ex.getCause().getLocalizedMessage();
        }
        return SUCCESS;
    }

    private String checkUnableToObtainConnectionException(UnableToObtainConnectionException ex) {
        if (ex.getCause() instanceof java.sql.SQLNonTransientConnectionException) {
            return DATABASE_ACCESS_ERROR + ex.getCause().getLocalizedMessage();
        } else if (ex.getCause() instanceof java.sql.SQLException) {
            return DATABASE_CONNECTION_ERROR + ex.getCause().getLocalizedMessage();
        } else {
            return UNEXPECTED_DATABASE_ERROR + ex.getCause().getLocalizedMessage();
        }
    }

    private String checkUnableToExecuteStatementException(UnableToExecuteStatementException ex) {
        if (ex.getCause() instanceof java.sql.SQLSyntaxErrorException) {
            return DATABASE_CONNECTION_ERROR + ex.getCause().getLocalizedMessage();
        } else {
            return UNEXPECTED_DATABASE_ERROR + ex.getCause().getLocalizedMessage();
        }
    }
}
