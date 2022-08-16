package com.tes.demo26.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tes.demo26.entity.Authority;

public interface AuthorityDao extends JpaRepository<Authority, Integer> {
    
}
