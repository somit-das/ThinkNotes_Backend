package com.notes.thinknotesbackend.config.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class CustomLoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("CustomLoggingFilter - Request Url :- "+request.getRequestURL());
        System.out.println("CustomLoggingFilter - Request Host :- "+request.getRemoteHost());
        filterChain.doFilter(request, response);
        System.out.println("CustomLoggingFilter - Response Status :- "+response.getStatus());
    }
}
