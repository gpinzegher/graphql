package com.demo.project.demo.controller;

import com.demo.project.demo.entity.Book;
import com.demo.project.demo.service.BookRatingService;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

@Controller
public class BookRatingController {
    private final BookRatingService service;

    public BookRatingController(BookRatingService service) {
        this.service = service;
    }

    @BatchMapping
    public Map<Book, Integer> rating(List<Book> books) {
        return service.getRatings(books);
    }
}