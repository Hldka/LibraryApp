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
import java.util.List;

@Controller
@RequestMapping("/courses")
public class bookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/form")
    public String showBooksForm(@ModelAttribute("books") Books books) {
        return "courseForm";

    }

    @PostMapping("/saveCourse")
    public String createBook(@Valid @ModelAttribute Books books, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "courseForm";
        }

        bookService.saveBooks(books);//saveOrUpdate
        return "redirect:/courses";
    }

    @GetMapping
    public ModelAndView getBooks(){
        List<Books> courseList=bookService.getAllBooks();
        ModelAndView mav=new ModelAndView();
        mav.addObject("courses",courseList);
        mav.setViewName("courses");
        return mav;
    }

    @GetMapping("/update")
    public String showUpdateForm(@RequestParam("id") Long id, Model model){
        Books books=bookService.findBooksById(id);
        model.addAttribute("course",books);
        return "courseForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteBooks(@PathVariable("id") Long id){
        bookService.deleteBooks(id);
        return "redirect:/courses";
    }



}
