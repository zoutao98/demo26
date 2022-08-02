package com.tes.demo26.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityAuthenticationEntryPoint securityAuthenticationEntryPoint;

    public SecurityConfig(final SecurityAuthenticationEntryPoint securityAuthenticationEntryPoint){
        this.securityAuthenticationEntryPoint = securityAuthenticationEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated();

        http.exceptionHandling().authenticationEntryPoint(securityAuthenticationEntryPoint);
    }
    

    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     http.cors().and().csrf().disable();

    //     http.authorizeRequests()
    //     .antMatchers("/login").hasRole("USER")
    //     .anyRequest().authenticated();

    //     // http.formLogin().disable();
    //     http.exceptionHandling()
    //             //.accessDeniedHandler(accessDeny) // 权限不足的时候的逻辑处理
    //             .authenticationEntryPoint(securityAuthenticationEntryPoint); // 未登录是的逻辑处理
    // }

}
