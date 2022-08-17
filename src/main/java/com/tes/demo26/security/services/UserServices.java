package com.tes.demo26.security.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.tes.demo26.dao.UserDao;
import com.tes.demo26.dao.UserMapper;
import com.tes.demo26.entity.AuthorityEntity;
import com.tes.demo26.entity.UserEntity;
import com.tes.demo26.security.SecurityUserDetails;
import com.tes.demo26.security.mapstruct.UserDetailsMapper;

@Service
public class UserServices implements UserDetailsService {

    private final UserDao userDao;

    private final UserMapper userMapper;

    public UserServices(final UserDao userDao, UserMapper userMapper){
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userDao.findUserByUsername(username);
        if (ObjectUtils.isEmpty(user)){
            throw new UsernameNotFoundException("用户不存在");
        }
        SecurityUserDetails securityUserDetails = UserDetailsMapper.MAPPER.from(user);
        List<AuthorityEntity> authorities = userMapper.findAuthorityByUserName(username);
        securityUserDetails.setAuthorities(authorities);
        return securityUserDetails;
    }

}
