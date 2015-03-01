package com.myblog.dao;


import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.myblog.dao.impl.CategoryDAOImpl;
import com.myblog.model.Blog;
import com.myblog.model.Category;

public class testCategoryDAO {
	private CategoryDAO dao = new CategoryDAOImpl();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreate() throws Exception {
		Category category = new Category();
		category.setName("个人");
		category.setPrivacy(0);
		dao.create(category);

		Category category2 = new Category();
		category2.setName("公开");
		category2.setPrivacy(1);
		dao.create(category2);
	}

	@Test
	public void testUpdate() throws Exception {
		Category category = dao.get(2);
		category.setName("alter");
		dao.update(category);
	}

	@Test
	public void testDelete() throws Exception {
		dao.delete(1);
	}

	@Test
	public void testGet() throws Exception {
		Category category = dao.get(1);
		System.out.println(category.getName());
	}

	@Test
	public void testGetBlogs() throws Exception {
		Category category = dao.get(1);
		List<Blog> list = dao.getBlogs(category);

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getId());
		}
	}

	@Test
	public void testList() throws Exception {
		List<Category> list = dao.list();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getName());
		}
	}

}
