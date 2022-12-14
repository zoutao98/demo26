package com.tes.demo26.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity(name = "t_authority")
public class AuthorityEntity implements GrantedAuthority {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer parentId;

    private String description;

    private String authority;

    public AuthorityEntity(){
        
    }

    public AuthorityEntity(Integer id, Integer parentId, String description, String authroity) {
        this.id = id;
        this.parentId = id;
        this.description = description;
        this.authority = authroity;
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
