package com.demo.project.demo.controller;

import com.demo.project.demo.entity.Book;
import com.demo.project.demo.service.SellingService;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.Set;

@Controller
public class SellingController {
    private final SellingService service;

    public SellingController(SellingService service) {
        this.service = service;
    }

    @BatchMapping(typeName = "Book", field = "qtySold")
    public Map<Book, Integer> qtySold(Set<Book> books) {
        return service.getSellings(books);
    }
}