package com.tes.demo26.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public ResponseEntity<String> index(){

        return ResponseEntity.ok("index");
    }
    
    @GetMapping("/hello")
    // @PreAuthorize(value = "hasAuthority('ADMIN')")
    @PreAuthorize(value = "hasAnyAuthority('api:hello', 'api:llo')")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello");
    }
}
