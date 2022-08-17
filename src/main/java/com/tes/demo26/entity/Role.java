package com.tes.demo26.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name = "t_role")
public class Role {
    
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;

    // @ManyToMany(mappedBy = "roles")
    // private List<User> users;
    
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    // public List<User> getUsers() {
    //     return users;
    // }
    // public void setUsers(List<User> users) {
    //     this.users = users;
    // }
    
}
