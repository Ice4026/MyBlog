package com.myblog.dao;

import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.myblog.dao.impl.BlogDAOImpl;
import com.myblog.dao.impl.CategoryDAOImpl;
import com.myblog.dao.impl.TagDAOImpl;
import com.myblog.dao.impl.UserDAOImpl;
import com.myblog.model.Blog;
import com.myblog.model.Category;
import com.myblog.model.Tag;
import com.myblog.model.User;

public class testBlogDAO {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void create() throws Exception {
		CategoryDAO cDAO = new CategoryDAOImpl();
		Category category = cDAO.get(1);
		
		TagDAO tDAO = new TagDAOImpl();
		Tag t1 = tDAO.get(1);
		Tag t2 = tDAO.get(2);
		Tag t3 = tDAO.get(3);
		Tag t4 = tDAO.get(4);
		
		UserDAO uDAO = new UserDAOImpl();
		User u = uDAO.get(1);
		
		BlogDAO dao = new BlogDAOImpl();
		Blog b = new Blog();
		b.setTitle("blog1");
		b.setContent("content1");
		b.setPublish_time(new Date());
		b.setCategory(category);
		b.setAuthor(u);
		dao.create(b);
		
		Blog b2 = new Blog();
		b2.setTitle("blog2");
		b2.setContent("content2");
		b2.setPublish_time(new Date());
		b2.setCategory(category);
		b2.getTags().add(t1);
		b2.getTags().add(t2);
		b2.getTags().add(t3);
		b2.getTags().add(t4);
		b2.setAuthor(u);
		dao.create(b2);
		
		Blog b3 = new Blog();
		b3.setTitle("blog3");
		b3.setContent("content3");
		b3.setPublish_time(new Date());
		b3.setCategory(category);
		b3.setAuthor(u);
		dao.create(b3);
	}
	
	@Test
	public void get() throws Exception{
		BlogDAO dao = new BlogDAOImpl();
		Blog b = dao.get(1);
		
		System.out.println(b.getTitle());
	}
	
	@Test
	public void update() throws Exception{
		BlogDAO dao = new BlogDAOImpl();
		Blog b = dao.get(1);
		b.setTitle("title1");
		
		dao.update(b);
	}
	
	@Test
	public void delete() throws Exception{
		BlogDAO dao = new BlogDAOImpl();
		dao.delete(1);
	}
	@Test
	public void list() throws Exception{
		BlogDAO dao = new BlogDAOImpl();
		List<Blog> l = dao.list(2);
				
		for (Blog b : l) {
			System.out.println(b.getId());
		}
	}
	@Test
	public void count() throws Exception{
		BlogDAO dao = new BlogDAOImpl();
		long count = dao.count(2);
		System.out.println(count);
	}
	@Test
	public void pagedList() throws Exception{
		BlogDAO dao = new BlogDAOImpl();
		List<Blog> list = dao.pagedList(1, 5, 2);
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).getId());
		}
	}
}
