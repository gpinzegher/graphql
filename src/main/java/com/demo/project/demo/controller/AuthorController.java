package com.demo.project.demo.controller;

import com.demo.project.demo.entity.Author;
import com.demo.project.demo.entity.Book;
import com.demo.project.demo.service.AuthorService;
import org.dataloader.DataLoader;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.execution.BatchLoaderRegistry;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Controller
public class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service, BatchLoaderRegistry batchLoaderRegistry) {
        this.service = service;

        batchLoaderRegistry
                .forTypePair(Book.class, Author.class)
                .registerMappedBatchLoader(
                        (books, env) -> {
                            return Mono.just(service.getAuthors(books));
                        }
                );
    }

    @SchemaMapping(typeName = "Book", field = "author")
    public CompletableFuture<Author> books(Book book, DataLoader<Book, Author> dataLoader) {
        return dataLoader.load(book);
    }
}