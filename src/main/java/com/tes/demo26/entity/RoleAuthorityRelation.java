package com.tes.demo26.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "t_role_authority_relation")
public class RoleAuthorityRelation {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer authorityId;
    private Integer roleId;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getAuthorityId() {
        return authorityId;
    }
    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    
}
