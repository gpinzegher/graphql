package com.demo.project.demo.controller;

import com.demo.project.demo.entity.Book;
import com.demo.project.demo.service.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Controller
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @QueryMapping
    public CompletableFuture<List<Book>> books(@Argument List<Integer> bookIds) {
        return CompletableFuture.supplyAsync(() -> service.getBooksByIds(bookIds));
    }
}