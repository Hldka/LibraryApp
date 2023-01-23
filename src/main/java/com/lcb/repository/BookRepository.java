package com.lcb.repository;

import com.lcb.domain.Books;

import java.util.List;
import java.util.Optional;

public interface BookRepository  {

    void save(Books books);

  //  void loan(Books books);

    List<Books> getAll();

    Optional<Books> findById(Long id);
 //   Optional<List<Books>> findByPersonName(String borrowername);
    void delete(Long id);


}
