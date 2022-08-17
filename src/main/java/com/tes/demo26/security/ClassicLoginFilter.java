package com.tes.demo26.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tes.demo26.security.dto.UserRequestDto;

public class ClassicLoginFilter extends UsernamePasswordAuthenticationFilter {

    public ClassicLoginFilter(AuthenticationManager authenticationManager){
        setAuthenticationManager(authenticationManager);

        setAuthenticationSuccessHandler((request, response, authentication) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();

            // String s = "{\"message\":\"登录成功\"}";
            String s = new ObjectMapper().writeValueAsString(authentication.getPrincipal());
            // String s = authentication.getPrincipal().toString();
            out.write(s);
            out.flush();
            out.close();
        });

        setAuthenticationFailureHandler((request, response, exception) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            String s = "{\"message\":\"登录失败\"}";
            out.write(s);
            out.flush();
            out.close();
        });

    }

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
                throw new BadCredentialsException("json格式错误没有找到用户名或密码");
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
