package com.tes.demo26.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final Logger log = LoggerFactory.getLogger(SecurityAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        log.info("judeg. {}", request.getRequestURI());

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(ResponseEntity.badRequest().body("需要登陆").getBody());
    }

}
