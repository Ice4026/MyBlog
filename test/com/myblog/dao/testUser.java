package com.myblog.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.myblog.dao.impl.UserDAOImpl;
import com.myblog.model.User;

public class testUser {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDelete() throws Exception {
		UserDAO d_user = new UserDAOImpl();
		d_user.delete(1);
	}
	@Test
	public void testValidate() throws Exception{
		UserDAO d_user = new UserDAOImpl();
		User user = d_user.validate("冰点星光", "12345");
		System.out.println(user.getName());
	}
}
