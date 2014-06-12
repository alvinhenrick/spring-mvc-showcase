package com.spock.example.controllers

import com.spock.example.services.BookService
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

/**
 * Created by jt on 9/11/13.
 */

@ContextConfiguration(locations = "/spring/si-config.xml")
class BookControllerWithSpringSpec extends Specification{

    MockMvc mockMvc
    BookController controller

    @Autowired
    BookService service

    def setup() {
        controller = new BookController()
        controller.setBookService(service)

        mockMvc = standaloneSetup(controller).build()
    }

    def "Test with SI Integration"(){
        given:
        def slurper = new JsonSlurper()

        when:
        def response = mockMvc.perform(get("/books/123123")).andReturn().getResponse()

        def rawJSON = response.getContentAsString()
        def json = slurper.parseText(rawJSON)

        then:
        json.title == "Spring in Action"
        json.isbn == "33778822"
    }
}
