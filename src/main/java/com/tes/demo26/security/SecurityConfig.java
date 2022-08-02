package com.tes.demo26.security;

import java.io.PrintWriter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityAuthenticationEntryPoint securityAuthenticationEntryPoint;

    public SecurityConfig(final SecurityAuthenticationEntryPoint securityAuthenticationEntryPoint) {
        this.securityAuthenticationEntryPoint = securityAuthenticationEntryPoint;
    }

    @Bean
    public TLoginFilter loginFilter() throws Exception {
        TLoginFilter tLoginFilter = new TLoginFilter();

        tLoginFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            
            // String s = "{\"message\":\"登录成功\"}";
            String s = new ObjectMapper().writeValueAsString(authentication.getPrincipal());
            out.write(s);
            out.flush();
            out.close();
        });

        tLoginFilter.setAuthenticationFailureHandler((request, response, exception) -> {
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            String s = "{\"message\":\"登录失败\"}";
            out.write(s);
            out.flush();
            out.close();
        });
        tLoginFilter.setAuthenticationManager(authenticationManagerBean());
        tLoginFilter.setFilterProcessesUrl("/login");
        return tLoginFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        // http.authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated();
        http.authorizeRequests().anyRequest().authenticated();

        http.formLogin().loginProcessingUrl("/login").permitAll();
        http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling().authenticationEntryPoint(securityAuthenticationEntryPoint);
    }

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    // http.cors().and().csrf().disable();

    // http.authorizeRequests()
    // .antMatchers("/login").hasRole("USER")
    // .anyRequest().authenticated();

    // // http.formLogin().disable();
    // http.exceptionHandling()
    // //.accessDeniedHandler(accessDeny) // 权限不足的时候的逻辑处理
    // .authenticationEntryPoint(securityAuthenticationEntryPoint); // 未登录是的逻辑处理
    // }

}
