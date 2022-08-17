package com.tes.demo26.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.tes.demo26.entity.AuthorityEntity;

@Mapper
public interface UserMapper {
    @Select(value = "SELECT t_authority.id, t_authority.authority ,t_authority.description, t_authority.parent_id FROM t_user "+
    "LEFT JOIN t_user_role_relation ON t_user.`id` = t_user_role_relation.user_id "+
    "LEFT JOIN t_role ON t_user_role_relation.role_id = t_role.`id`"+
    "LEFT JOIN t_role_authority_relation ON t_role.`id` = t_role_authority_relation.`role_id`"+
    "LEFT JOIN t_authority ON t_role_authority_relation.`authority_id` = t_authority.`id`"+
    "WHERE t_user.username = #{username}")
    List<AuthorityEntity> findAuthorityByUserName(String username);
}
