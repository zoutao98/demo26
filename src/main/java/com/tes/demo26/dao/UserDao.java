package com.tes.demo26.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tes.demo26.entity.User;

public interface UserDao extends JpaRepository<User, Integer> {

    User findUserByUsername(String username);
}
