package com.spock.example.controllers

import groovyx.net.http.RESTClient
import spock.lang.Specification

/**
 * Created by jt on 9/12/13.
 */
class RestClientTest extends Specification {
    RESTClient restClient = new RESTClient('http://localhost:8080/')

    def "test rest client"(){

        when:
        def response = restClient.get(path: '/books' + '/123123' )

        then:
        response != null
        response.data.title == 'Spring in Action'
    }
}
