package com.spock.example.gateway;

import com.spock.example.model.Book;
import org.springframework.integration.annotation.Gateway;

/**
 * Created by jt on 9/11/13.
 */
public interface BookGateway {

    @Gateway (requestChannel = "bookChannel")
    Book findBookByISBN(String ISBN);
}
