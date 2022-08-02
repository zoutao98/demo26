package com.tes.demo26;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.tes.demo26.dao.UserDao;
import com.tes.demo26.entity.Role;
import com.tes.demo26.entity.User;

@SpringBootTest
class Demo26ApplicationTests {

	@Resource
	UserDao userDao;

	@Test
	void contextLoads() {
		User u1 = new User();
		u1.setUsername("zoutao");
		u1.setPassword("123123");
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
	}

}
