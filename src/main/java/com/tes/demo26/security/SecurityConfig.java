package com.tes.demo26.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.tes.demo26.security.jwt.JwtAuthenticationFilter;
import com.tes.demo26.security.jwt.JwtAuthorizationFilter;
import com.tes.demo26.security.services.UserServices;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${jwtEnable:true}")
    private boolean jwtEnable;

    private final SecurityAuthenticationEntryPoint securityAuthenticationEntryPoint;
    private final UserServices userServices;

    public SecurityConfig(final SecurityAuthenticationEntryPoint securityAuthenticationEntryPoint,
            final UserServices userServices) {
        this.securityAuthenticationEntryPoint = securityAuthenticationEntryPoint;
        this.userServices = userServices;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        http.headers().frameOptions().sameOrigin();
        // http.authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated();
        // 拦截所有请求 .antMatchers("/hello").hasAuthority("api:hello")

        if (jwtEnable) {
            http.authorizeRequests().antMatchers("/authentication/login", "/h2-console/**", "/error").permitAll();
            http.authorizeRequests().anyRequest().authenticated();
            http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

            // http.formLogin().loginProcessingUrl("/login").permitAll();

            // http.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
            http.addFilterAt(new JwtAuthenticationFilter(authenticationManagerBean()),
                    UsernamePasswordAuthenticationFilter.class)
                    .addFilter(new JwtAuthorizationFilter(authenticationManagerBean()));
        }
        // 未认证请求
        http.exceptionHandling().authenticationEntryPoint(securityAuthenticationEntryPoint);
    }

    public PasswordEncoder passwordEncoder() {
        // return new BCryptPasswordEncoder();
        String idForEncode = "bcrypt";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(idForEncode, new BCryptPasswordEncoder());
        encoders.put(null, new BCryptPasswordEncoder());
        return new DelegatingPasswordEncoder(idForEncode, encoders);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // super.configure(auth);
        auth.userDetailsService(userServices).passwordEncoder(passwordEncoder());
    }

}
