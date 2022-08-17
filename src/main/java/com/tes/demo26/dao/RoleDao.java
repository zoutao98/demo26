package com.tes.demo26.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tes.demo26.entity.RoleEntity;

public interface RoleDao extends JpaRepository<RoleEntity, Integer> {
    
}
