package com.lcb.controller;

import com.lcb.domain.Books;
import com.lcb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/book")
public class bookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/form")
    public String showBooksForm(@ModelAttribute("books") Books books) {
        return "bookForm";

    }

    @PostMapping("/saveBooks")
    public String createBook(@Valid @ModelAttribute Books books, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "bookForm";
        }

        bookService.saveBooks(books);//saveOrUpdate
        return "redirect:/book";
    }



    @GetMapping
    public ModelAndView getAllBooks(){
        List<Books> booksList=bookService.getAllBooks();
        ModelAndView mav=new ModelAndView();
        mav.addObject("book",booksList);
        mav.setViewName("book");
        return mav;
    }
    @GetMapping("/update")
    public String showLoanForm (@RequestParam("id") Long id, Model model){

        Books books = bookService.findBooksById(id);
        books.setCheckOutedDate(LocalDateTime.now());
        books.setReturnDate(LocalDateTime.now().plusDays(15L));
        model.addAttribute("book",books);
        return "bookForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteBooks(@PathVariable("id") Long id){
        bookService.deleteBooks(id);
        return "redirect:/book";
    }



}
