package com.spock.example.simple

import org.springframework.samples.mvc.simple.SimpleController
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by jt on 9/11/13.
 */
class SimpleControllerSpec extends Specification{

    MockMvc mockMvc

    def setup(){
        mockMvc = standaloneSetup(new SimpleController()).build()
    }

    def "Test Simple Controller Get Action"(){
        when:
        def response = mockMvc.perform(get("/simple"))

        then:
        response.andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(content().string("Hello world!"))
    }
}
