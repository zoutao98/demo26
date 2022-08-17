package com.tes.demo26.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tes.demo26.entity.UserRoleRelationEntity;

public interface UserRoleRelationDao extends JpaRepository<UserRoleRelationEntity, Integer> {
    
}
