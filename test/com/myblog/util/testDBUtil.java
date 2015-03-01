package com.myblog.util;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testDBUtil {
	private DBUtil util;

	@Before
	public void setUp() throws Exception {
		util = new DBUtil();
	}

	@After
	public void tearDown() throws Exception {
		util.closeSessionFactory();
	}

	@Test
	public void getCurrentSession() {
		Session session = util.getCurrentSession();
		System.out.println(session);
	}

}
