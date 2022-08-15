package com.tes.demo26.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private static final String HEADE_STRING = "Authorization";

    private static final String PREFIX_STRING = "Bearer";

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String headeString = request.getHeader(HEADE_STRING);
        if (null != headeString && headeString.startsWith(PREFIX_STRING)) {

        }
        super.doFilterInternal(request, response, chain);
    }

}
