package com.demo.project.demo.controller;

import com.demo.project.demo.entity.Author;
import com.demo.project.demo.entity.Book;
import com.demo.project.demo.service.AuthorService;
import org.dataloader.DataLoader;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.execution.BatchLoaderRegistry;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Controller
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @BatchMapping(typeName = "Book", field = "author")
    public Map<Book, Author> author(Set<Book> books) {
        return service.getAuthors(books);
    }
}