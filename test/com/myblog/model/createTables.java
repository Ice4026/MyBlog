package com.myblog.model;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.myblog.dao.testBlogDAO;
import com.myblog.dao.testCategoryDAO;
import com.myblog.dao.testTagDAO;
import com.myblog.util.DBUtil;

public class createTables {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		new SchemaExport(new Configuration().configure()).create(true, true);
	}
	
	@Test
	public void initialTables() throws Exception{
//		test();
		new testCategoryDAO().testCreate();
		new testTagDAO().testCreate();
		new testBlogDAO().create();
	}
	
	@Test
	public void testSQL(){
		DBUtil util = new DBUtil();
		Session session = util.getCurrentSession();
		session.beginTransaction();
		Query query = session.createSQLQuery("delete from blog_tag where tags_id=4");
		query.executeUpdate();
		session.getTransaction().commit();
	}
}
