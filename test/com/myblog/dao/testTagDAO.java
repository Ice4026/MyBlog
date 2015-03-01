package com.myblog.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.myblog.dao.impl.TagDAOImpl;
import com.myblog.model.Tag;

public class testTagDAO {
	private TagDAO dao = new TagDAOImpl();
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreate() throws Exception {
		Tag tag = new Tag();
		tag.setName("tag1");
		dao.create(tag);
		
		Tag tag2 = new Tag();
		tag2.setName("tag2");
		dao.create(tag2);
		
		Tag tag3 = new Tag();
		tag3.setName("tag3");
		dao.create(tag3);
		
		Tag tag4 = new Tag();
		tag4.setName("tag4");
		dao.create(tag4);
	}

	@Test
	public void testUpdate() throws Exception {
		Tag tag = dao.get(2);
		tag.setName("alter");
		dao.update(tag);
	}

	@Test
	public void testDelete() throws Exception {
		dao.delete(3);
	}

}
