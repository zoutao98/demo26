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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tes.demo26.dao.UserDao;
import com.tes.demo26.entity.Role;
import com.tes.demo26.entity.User;

@SpringBootTest
class Demo26ApplicationTests {

	private final Logger log = LoggerFactory.getLogger(Demo26ApplicationTests.class);

	@Resource
	UserDao userDao;

	@Test
	void contextLoads() {
		User u1 = new User();
		u1.setUsername("zoutao");
		u1.setPassword("$2a$10$dn7JhlvNmUc./aqgHJ/KzuqatucliauOiCSw4jTaxnLl47ErhH0pS");
		u1.setAccountNonExpired(true);
		u1.setAccountNonLocked(true);
		u1.setCredentialsNonExpired(true);
		u1.setEnabled(true);
		List<Role> rs1 = new ArrayList<>();
		Role r1 = new Role();
		r1.setName("ROLE_admin");
		rs1.add(r1);
		u1.setRoles(rs1);
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

}
