package com.tes.demo26.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tes.demo26.entity.RoleAuthorityRelation;

public interface RoleAuthorityDao extends JpaRepository<RoleAuthorityRelation, Integer> {
    
}
