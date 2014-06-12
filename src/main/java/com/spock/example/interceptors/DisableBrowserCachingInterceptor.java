package com.spock.example.interceptors;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.MessageFactory;

public class DisableBrowserCachingInterceptor extends HandlerInterceptorAdapter {

    public void postHandle(final HttpServletRequest request, final HttpServletResponse response,
                           final Object handler, final ModelAndView modelAndView) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies
    }



}
