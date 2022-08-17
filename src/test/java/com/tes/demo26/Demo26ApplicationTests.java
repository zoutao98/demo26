package com.tes.demo26;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tes.demo26.dao.AuthorityDao;
import com.tes.demo26.dao.RoleAuthorityDao;
import com.tes.demo26.dao.RoleDao;
import com.tes.demo26.dao.UserDao;
// import com.tes.demo26.dao.UserRoleRelationDao;
import com.tes.demo26.entity.Role;
import com.tes.demo26.entity.User;
import com.tes.demo26.security.jwt.JwtUtils;

@SpringBootTest
class Demo26ApplicationTests {

	private final Logger log = LoggerFactory.getLogger(Demo26ApplicationTests.class);
	@Resource
	AuthorityDao authorityDao;
	@Resource
	RoleDao roleDao;
	@Resource
	RoleAuthorityDao roleAuthorityDao;
	@Resource
	UserDao userDao;
	// @Resource
	// UserRoleRelationDao userRoleRelationDao;

	@Test
	@Modifying()
	void dataBaseInit() {
		User u1 = new User();
		u1.setUsername("zoutao");
		u1.setPassword("{bcrypt}$2a$10$pDcQHZtu0/Y.2VLbI4Y31O9MlXmvl5sQ8nB20t58rl6glkrAPRjzu");
		u1.setAccountNonExpired(true);
		u1.setAccountNonLocked(true);
		u1.setCredentialsNonExpired(true);
		u1.setEnabled(true);

		Role r1 = new Role();
		r1.setName("admin");
		r1.setDescription("管理员");
		List<Role> rs1 = new ArrayList<>();
		rs1.add(r1);

		// u1.setRoles(rs1);
		
		//userDao.deleteById(1);
		userDao.save(u1);

		// User u1 = new User();
		// u1.setUsername("zoutao");
		// u1.setPassword("123123");
		// u1.setAccountNonExpired(true);
		// u1.setAccountNonLocked(true);
		// u1.setCredentialsNonExpired(true);
		// u1.setEnabled(true);
		// List<Role> rs1 = new ArrayList<>();
		// Role r1 = new Role();
		// r1.setName("ROLE_admin");
		// rs1.add(r1);
		// u1.setRoles(rs1);
		// userDao.delete(u1);
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
