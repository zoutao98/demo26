package com.tes.demo26.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.tes.demo26.dao.UserDao;
import com.tes.demo26.entity.User;

@Service
public class UserServices implements UserDetailsService {

    private final UserDao userDao;

    public UserServices(final UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByUsername(username);
        if (ObjectUtils.isEmpty(user)){
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }

}
