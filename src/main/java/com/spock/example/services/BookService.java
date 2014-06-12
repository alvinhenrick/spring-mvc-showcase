package com.spock.example.services;

import com.spock.example.model.Book;

/**
 * Created by jt on 9/11/13.
 */
public interface BookService {

    Book findBookByISBN(String ISBN);

    void saveBook(Book book);
}
