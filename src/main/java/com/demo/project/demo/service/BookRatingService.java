package com.demo.project.demo.service;

import com.demo.project.demo.ThreadHelper;
import com.demo.project.demo.entity.Book;
import com.demo.project.demo.entity.Rating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookRatingService {

    private static final Logger log = LoggerFactory.getLogger(BookRatingService.class);
//---------------------bookId, rating
    private final List<Rating> bookRatings = List.of(
            new Rating(1, 4),
            new Rating(1, 4),
            new Rating(1, 3),
            new Rating(1, 1),
            new Rating(2, 5),
            new Rating(2, 4),
            new Rating(2, 3),
            new Rating(2, 4),
            new Rating(3, 4),
            new Rating(3, 4));

    public BookRatingService() {}

    public Map<Book, Integer> getRatings(List<Book> books) {
        ThreadHelper.log(log, Thread.currentThread(), BookRatingService.class, "getRatings");
        ThreadHelper.sleep(1000);
        return books.stream().collect(Collectors.toMap(book -> book, book -> getRatingForBook(book, bookRatings)));
    }

    private Integer getRatingForBook(Book book, List<Rating> bookRatings) {
        Integer sum = bookRatings.stream().filter(r -> r.bookId() == book.bookId()).map(Rating::rating).reduce(0, Integer::sum);
        Integer count = Math.toIntExact(bookRatings.stream().filter(r -> r.bookId() == book.bookId()).count());
        return count > 0 ? sum/count : -1;
    }
}
