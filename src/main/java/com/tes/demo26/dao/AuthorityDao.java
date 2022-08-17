package com.tes.demo26.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tes.demo26.entity.AuthorityEntity;

public interface AuthorityDao extends JpaRepository<AuthorityEntity, Integer> {
    
}
