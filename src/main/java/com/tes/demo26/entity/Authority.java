package com.tes.demo26.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;


@Entity(name = "t_authority")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue
    private Integer id;

    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
