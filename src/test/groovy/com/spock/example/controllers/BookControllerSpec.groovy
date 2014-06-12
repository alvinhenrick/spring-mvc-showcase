package com.spock.example.controllers

import com.spock.example.model.Book
import com.spock.example.services.BookService
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
/**
 * Created by jt on 9/11/13.
 */
class BookControllerSpec extends Specification {

    MockMvc mockMvc
    BookController controller
    BookService service

    def setup() {
        //build controller and inject mock of service
        controller = new BookController()
        service = Mock(BookService)
        controller.setBookService(service)

        mockMvc = standaloneSetup(controller).build()
    }

    def "Test Get Action of Controller"() {
        given:
        Book book = new Book(title: "Grails Rocks", ISBN: '48483922334')

        when:
        def response = mockMvc.perform(get("/books/123123"))

        then:
        1 * service.findBookByISBN(_) >> book
        response.andExpect(status().isOk())
    }

    def "Test Get Action of Controller verify parameter is sent to service"() {
        given:
        Book book = new Book(title: "Grails Rocks", ISBN: '48483922334')

        when:
        def response = mockMvc.perform(get("/books/123123"))

        then:
        1 * service.findBookByISBN({it == '123123'}) >> book
        response.andExpect(status().isOk())
    }

    def "Test Get Action of Controller test JSON Output"() {
        given:
        Book book = new Book(title: "Grails Rocks", ISBN: '48483922334')
        def slurper = new JsonSlurper()
        when:
        def response = mockMvc.perform(get("/books/123123")).andReturn().getResponse()

        def rawJSON = response.getContentAsString()
        def json = slurper.parseText(rawJSON)

        then:
        1 * service.findBookByISBN(_) >> book
        json.title == book.title
        json.isbn == book.ISBN
    }


    def "Test Save Book"(){
        given:
        Book book = new Book(title: "GOF Design Patterns", ISBN: "99888998")

        def jsonBuilder = new JsonBuilder()

        jsonBuilder.'com.incept5.model.book'{
            title book.title
            isbn book.ISBN
        }
        def jsonString = jsonBuilder.toString()

        when:
        def response = mockMvc.perform(post("/book")
                .contentType(MediaType.APPLICATION_JSON)
        .content(jsonString)).andReturn().getResponse()

        then:
        1 * service.saveBook(_)



    }
}
