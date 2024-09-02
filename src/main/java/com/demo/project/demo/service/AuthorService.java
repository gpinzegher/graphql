package com.demo.project.demo.service;

import com.demo.project.demo.ThreadHelper;
import com.demo.project.demo.entity.Author;
import com.demo.project.demo.entity.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private static final Logger log = LoggerFactory.getLogger(AuthorService.class);

    private final List<Author> authors = List.of(
            new Author(1, "First 1","Last 1"),
            new Author(2, "First 2","Last 2"),
            new Author(3, "First 3","Last 3"),
            new Author(4, "First 4","Last 4"),
            new Author(5, "First 5","Last 5"),
            new Author(6, "First 6","Last 6")
    );

    public AuthorService() { }

    public Map<Book, Author> getAuthors(Set<Book> books) {

        ThreadHelper.log(log, Thread.currentThread(), AuthorService.class, "getAuthors");
        //process that takes some time
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return books.stream().collect(Collectors.toMap(book -> book, book -> getAuthorForBook(book, authors)));
    }

    private Author getAuthorForBook(Book book, List<Author> authors) {
        return authors.stream().filter(author -> author.authorId() == book.authorId()).findFirst().orElse(null);
    }
}
