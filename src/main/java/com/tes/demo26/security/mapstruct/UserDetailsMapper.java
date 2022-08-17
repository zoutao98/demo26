package com.tes.demo26.security.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.tes.demo26.entity.UserEntity;
import com.tes.demo26.security.SecurityUserDetails;

@Mapper
public interface UserDetailsMapper {

    public static UserDetailsMapper MAPPER = Mappers.getMapper(UserDetailsMapper.class);

    @Mappings({
        @Mapping(source = "user.username", target = "username"),
        @Mapping(source = "user.password", target = "password"),
        @Mapping(source = "user.accountNonExpired", target = "accountNonExpired"),
        @Mapping(source = "user.accountNonLocked", target = "accountNonLocked"),
        @Mapping(source = "user.credentialsNonExpired", target = "credentialsNonExpired"),
        @Mapping(source = "user.enabled", target = "enabled"),
    })
    public SecurityUserDetails from(UserEntity user);
    
}
