package com.lcb.service;

import com.lcb.domain.Books;

import java.util.List;
import java.util.Optional;

public interface BookService {
    void saveBooks(Books books);

    void loanBooks(Books books);

    List<Books> getAllBooks();
    Books findBooksById(Long id);
    List<Books> findBooksByPersonName(String borrowername);
    void deleteBooks(Long id);
}
