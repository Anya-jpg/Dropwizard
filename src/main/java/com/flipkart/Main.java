package com.flipkart;

import com.flipkart.beans.Author;
import com.flipkart.beans.Book;
import com.flipkart.dao.AuthorDao;
import com.flipkart.dao.BookDao;
import com.flipkart.service.UserService;
import org.skife.jdbi.v2.DBI;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        DBI dbi = new DBI("jdbc:mysql://127.0.0.1:3306/temp?autoReconnect=true&useSSL=false", "root", "Fk!@#%213501");

        AuthorDao authorDao = dbi.onDemand(AuthorDao.class);
        BookDao bookDao = dbi.onDemand(BookDao.class);

        UserService userService = new UserService(authorDao, bookDao);

        Scanner sc = new Scanner(System.in);

        int choice;

        while (true) {
            System.out.println("----------Welcome to Course Registration System---------");
            System.out.println("1 --> Get a list of all authors");
            System.out.println("2 --> Get an author by id");
            System.out.println("3 --> Given an author id return his books");
            System.out.println("4 --> Get all the books");
            System.out.println("5 --> Add a new book");
            System.out.println("6 --> Add a new author");
            System.out.println("0 --> Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    List<Author> authors = userService.getAllAuthors();
                    authors.forEach(author -> System.out.println(author.getAuthorName()));
                    break;
                case 2:
                    System.out.print("Enter Author ID: ");
                    int authorId = sc.nextInt();
                    Author author = userService.getAuthorById(authorId);
                    System.out.println("Author: " + author.getAuthorName());
                    break;
                case 3:
                    System.out.print("Enter Author ID: ");
                    int authorIdForBooks = sc.nextInt();
                    List<Book> booksByAuthor = userService.getAuthorBooks(authorIdForBooks);
                    booksByAuthor.forEach(book -> System.out.println(book.getBookName()));
                    break;
                case 4:
                    List<Book> allBooks = userService.getAllBooks();
                    allBooks.forEach(book -> System.out.println(book.getBookName()));
                    break;
                case 5:
                    System.out.print("Enter Book ID: ");
                    int bookId = sc.nextInt();
                    System.out.print("Enter Book Name: ");
                    sc.nextLine(); // consume newline
                    String bookName = sc.nextLine();
                    System.out.print("Enter Author ID: ");
                    int authorIdForBook = sc.nextInt();
                    userService.addBookById(new Book(bookId, bookName, authorIdForBook));
                    System.out.println("Book added.");
                    break;
                case 6:
                    System.out.print("Enter Author ID: ");
                    int newAuthorId = sc.nextInt();
                    System.out.print("Enter Author Name: ");
                    sc.nextLine(); // consume newline
                    String newAuthorName = sc.nextLine();
                    Author author2 = new Author(newAuthorName, newAuthorId);
                    userService.addAuthorById(author2);
                    System.out.println("Author added.");
                    break;
                case 0:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid Input");
            }
        }
    }
}
