package com.spock.example.interceptors

import org.springframework.samples.mvc.simple.SimpleController
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.servlet.HandlerInterceptor
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class DisableBrowserCachingInterceptorTest extends Specification {

    def controller
    MockMvc mockMvc

    def setup() {
        controller = new SimpleController()

        HandlerInterceptor interceptor = new DisableBrowserCachingInterceptor()

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .addInterceptors(interceptor)
                .build();
    }

    def "Test Get"() {
        when:
        def result = mockMvc.perform(get('/simple')).andReturn()
        then:
        result.response.headerNames.size() == 5
        result.response.getHeader('Cache-Control')
        result.response.getHeader('Pragma')
        result.response.getHeader('Expires')
    }

}