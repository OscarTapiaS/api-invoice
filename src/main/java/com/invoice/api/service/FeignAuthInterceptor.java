package com.invoice.api.service;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class FeignAuthInterceptor implements RequestInterceptor {

    private final ObjectFactory<HttpServletRequest> requestFactory;

    @Autowired
    public FeignAuthInterceptor(ObjectFactory<HttpServletRequest> requestFactory) {
        this.requestFactory = requestFactory;
    }

    @Override
    public void apply(RequestTemplate template) {
        HttpServletRequest request = requestFactory.getObject();

        String token = request.getHeader("Authorization");
        System.out.println("[FeignAuthInterceptor] TOKEN = " + token);

        if (token != null && !token.isBlank()) {
            template.header("Authorization", token);
        }
    }
}