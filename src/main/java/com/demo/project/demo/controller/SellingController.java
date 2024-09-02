package com.demo.project.demo.controller;

import com.demo.project.demo.entity.Book;
import com.demo.project.demo.service.SellingService;
import org.dataloader.DataLoader;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.execution.BatchLoaderRegistry;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Controller
public class SellingController {
    private final SellingService service;

    public SellingController(SellingService service, BatchLoaderRegistry batchLoaderRegistry) {
        this.service = service;

        batchLoaderRegistry
                .forTypePair(Book.class, Integer.class)
                .registerMappedBatchLoader(
                        (books, env) -> {
                            return Mono.just(service.getSellings(books));
                        }
                );
    }

    @SchemaMapping(typeName = "Book", field = "qtySold")
    public CompletableFuture<Integer> qtySold(Book book, DataLoader<Book, Integer> dataLoader) {
        return dataLoader.load(book);
    }
}