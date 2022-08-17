package com.tes.demo26.security.jwt;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tes.demo26.dao.SpringUtil;
import com.tes.demo26.dao.UserMapper;
import com.tes.demo26.entity.AuthorityEntity;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    private static final String HEADE_STRING = "Authorization";

    private static final String PREFIX_STRING = "Bearer";

    private UserMapper userMapper;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        userMapper = SpringUtil.getBean(UserMapper.class);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String token = request.getHeader(HEADE_STRING);
        if (null != token && token.startsWith(PREFIX_STRING)) {
            log.info(token);
            UsernamePasswordAuthenticationToken authentication = getAuthenticationToken(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        }else{
            super.doFilterInternal(request, response, chain);
        }
    }

    public UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
        if (StringUtils.hasText(token)) {

            token = token.replaceFirst(PREFIX_STRING, "").trim();
            if(!StringUtils.hasText(token)){
                return null;
            }

            String objectString = JwtUtils.parseJwtToken(token);
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode jsonNode = objectMapper.readTree(objectString);
                List<AuthorityEntity> authorities = userMapper.findAuthorityByUserName(jsonNode.get("username").asText());
                return new UsernamePasswordAuthenticationToken(
                        jsonNode.get("username").asText(), null, authorities);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            log.info(objectString);
            if (StringUtils.hasText(objectString)) {
                return null;
            }
        }
        return null;

    }

}
