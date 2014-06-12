package com.spock.example.controllers;

import com.spock.example.model.Book;
import com.spock.example.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by jt on 9/11/13.
 */
@Controller
public class BookController {

    private BookService bookService;

    @RequestMapping(value = "/books/{isbn}",
            method = RequestMethod.GET, produces="application/json")
    public @ResponseBody Book getBook(@PathVariable String isbn){
       return bookService.findBookByISBN(isbn);
    }

    @RequestMapping(value="/book", method = RequestMethod.POST)
    public @ResponseBody String save(Book book){

        bookService.saveBook(book);

        return "saved";
    }
    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
}
