package com.tes.demo26.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.tes.demo26.entity.AuthorityEntity;
import com.tes.demo26.entity.UserEntity;

public interface UserDao extends JpaRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity> {

    UserEntity findUserByUsername(String username);

    // @Query("SELECT new AuthorityEntity FROM UserEntity LEFT JOIN UserRoleRelationEntity ON UserEntity.id = UserRoleRelationEntity.userId LEFT JOIN RoleEntity ON UserRoleRelationEntity.roleId = RoleEntity.id LEFT JOIN RoleAuthorityRelationEntity ON RoleEntity.id = RoleAuthorityRelationEntity.roleId LEFT JOIN AuthorityEntity ON RoleAuthorityRelationEntity.authorityId = AuthorityEntity.id WHERE UserEntity.username = ?1")
    // List<AuthorityEntity> findAuthorityByUserName(String username);

    // @Query(value ="select t_authority from t_authority", nativeQuery = true)
    // Collection<Object[]> findAuthorityByUserName(String username);
    // @Query("select new AuthorityEntity(t_user.id, t_user.id, t_user.username, t_user.password) from t_user")
    // List<AuthorityEntity> findAuthorityByUserName(String username);
    // @Query(
    // value = "SELECT id, username authority, PASSWORD description, id parentId FROM t_user "+
    // "WHERE username = ?1", nativeQuery = true)
    // List<Object[]> findAuthorityByUserName(String username);
    // @Query(
    // value = "SELECT new AuthorityEntity(t_authority.id, t_authority.parent_id, t_authority.description, t_authority.authority) FROM t_user"+
    // "LEFT JOIN t_user_role_relation ON t_user.id = t_user_role_relation.user_id"+
    // "LEFT JOIN t_role ON t_user_role_relation.role_id = t_role.id"+
    // "LEFT JOIN t_role_authority_relation ON t_role.id = t_role_authority_relation.role_id"+
    // "LEFT JOIN t_authority ON t_role_authority_relation.authority_id = t_authority.id"+
    // "WHERE t_user.username = ?1", nativeQuery = true)
    // List<AuthorityEntity> findAuthorityByUserName(String username);
}
