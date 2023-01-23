package com.lcb.service;

import com.lcb.domain.Books;
import com.lcb.exception.NoSuchIdException;
import com.lcb.exception.NoSuchPersonalException;
import com.lcb.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;
    @Override
    public void saveBooks(Books books) {
       bookRepository.save(books);

    }

   // @Override
   // public void loanBooks(Books books) {
   //     bookRepository.loan(books);}

    @Override
    public List<Books> getAllBooks() {
        return bookRepository.getAll();
    }

    @Override
    public Books findBooksById(Long id) {
        return bookRepository.findById(id).orElseThrow(()->new NoSuchIdException("There is no id  realetd with this quers "+id));
    }

   // @Override
   // public List<Books> findBooksByPersonName(String borrowername) {
     //   return bookRepository.findByPersonName(borrowername).orElseThrow(()->
       //         new NoSuchPersonalException("There is no id  realetd with this quers "+borrowername));
    //}

    @Override
    public void deleteBooks(Long id) {
        bookRepository.delete(id);

    }
}
