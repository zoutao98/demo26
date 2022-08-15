package com.tes.demo26.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tes.demo26.jwt.UserRequestDto;

public class TLoginFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (!"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        if (MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType())) {
            UserRequestDto loginInfo = null;
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                loginInfo = objectMapper.readValue(request.getInputStream(), UserRequestDto.class);
            } catch (IOException e) {
                e.printStackTrace();
                throw new BadCredentialsException("json格式错误，没有找到用户名或密码");
            }
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    loginInfo.getUsername().trim(), loginInfo.getPassword().trim());
            setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        } else {
            return super.attemptAuthentication(request, response);
        }
    }
}
