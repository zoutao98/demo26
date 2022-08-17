package com.tes.demo26;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tes.demo26.dao.AuthorityDao;
import com.tes.demo26.dao.RoleAuthorityDao;
import com.tes.demo26.dao.RoleDao;
import com.tes.demo26.dao.UserDao;
import com.tes.demo26.dao.UserRoleRelationDao;
import com.tes.demo26.entity.AuthorityEntity;
import com.tes.demo26.entity.RoleAuthorityRelationEntity;
import com.tes.demo26.entity.RoleEntity;
import com.tes.demo26.entity.UserEntity;
import com.tes.demo26.entity.UserRoleRelationEntity;
import com.tes.demo26.security.jwt.JwtUtils;

@SpringBootTest
class Demo26ApplicationTests {

	private final Logger log = LoggerFactory.getLogger(Demo26ApplicationTests.class);
	@Resource
	AuthorityDao authorityDao;
	@Resource
	RoleAuthorityDao roleAuthorityDao;
	@Resource
	RoleDao roleDao;
	@Resource
	UserDao userDao;
	@Resource
	UserRoleRelationDao userRoleRelationDao;

	@Test
	void dataBaseInit() {
		UserEntity u1 = new UserEntity();
		u1.setId(1);
		u1.setUsername("zoutao");
		u1.setPassword("{bcrypt}$2a$10$pDcQHZtu0/Y.2VLbI4Y31O9MlXmvl5sQ8nB20t58rl6glkrAPRjzu");
		u1.setAccountNonExpired(true);
		u1.setAccountNonLocked(true);
		u1.setCredentialsNonExpired(true);
		u1.setEnabled(true);

		userDao.save(u1);

		RoleEntity r1 = new RoleEntity();
		r1.setId(1);
		r1.setName("ROLE_admin");
		r1.setDescription("管理员");
		roleDao.save(r1);

		AuthorityEntity a1 = new AuthorityEntity();
		a1.setId(1);
		a1.setAuthority("api:hello");
		a1.setDescription("hello");
		authorityDao.save(a1);

		UserRoleRelationEntity ur1 = new UserRoleRelationEntity();
		ur1.setId(1);
		ur1.setUserId(1);
		ur1.setRoleId(1);
		userRoleRelationDao.save(ur1);

		RoleAuthorityRelationEntity ra1 = new RoleAuthorityRelationEntity();
		ra1.setId(2);
		ra1.setRoleId(1);
		ra1.setAuthorityId(1);
		roleAuthorityDao.save(ra1);
	}

	@Test
	void password() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodePasswod = encoder.encode("123123");
		log.info("{}", encodePasswod);
	}

	@Test
	void password2() {
		String idForEncode = "bcrypt";
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put(idForEncode, new BCryptPasswordEncoder());
		DelegatingPasswordEncoder encoder = new DelegatingPasswordEncoder(idForEncode, encoders);
		String encodePasswod = encoder.encode("123123");
		log.info("{}", encodePasswod);
	}

	@Test
	void jwtToken() {
		log.info("token:{}", JwtUtils.createJwtToken("zoutao"));
	}



}
