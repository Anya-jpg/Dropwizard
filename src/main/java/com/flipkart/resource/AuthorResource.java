package com.flipkart.resource;

import com.flipkart.beans.Author;
import com.flipkart.beans.Book;
import com.flipkart.dao.AuthorDao;
import com.flipkart.service.UserService;
import com.wordnik.swagger.annotations.Api;

import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "author", description = "Author Resource for performing operations on Author and Book Tables")
public class AuthorResource {

    private static final Logger logger = LoggerFactory.getLogger(AuthorResource.class);
    private final UserService userService;

    public AuthorResource(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAuthor(Author author) {
        userService.addAuthorById(author);
        return Response.ok().build();
    }


    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Author author) {
        userService.addAuthorById(author);
        return Response.ok().build();
    }

    @GET
    public Response getAllAuthors() {
        List<Author> authors = userService.getAllAuthors();
        return Response.ok(authors).build();
    }

    @GET
    @Path("/{authorId}")
    public Response getAuthorById(@PathParam("authorId") int authorId) {
        Author author = userService.getAuthorById(authorId);
        return Response.ok(author).build();
    }

    @GET
    @Path("/{authorId}/books")
    public Response getAuthorBooks(@PathParam("authorId") int authorId) {
        List<Book> books = userService.getAuthorBooks(authorId);
        return Response.ok(books).build();
    }

    @GET
    public Response getAllBooks() {
        List<Book> books = userService.getAllBooks();
        return Response.ok(books).build();
    }

}
