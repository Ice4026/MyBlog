package com.myblog.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import com.myblog.dao.UserDAO;
import com.myblog.model.Blog;
import com.myblog.model.User;
import com.myblog.util.DBUtil;

public class UserDAOImpl implements UserDAO {
	DBUtil util = new DBUtil();

	@Override
	public User get(int id) throws Exception {
		Session session = util.getCurrentSession();
		
		try {
			session.beginTransaction();
			User user = (User)session.get(User.class, id);
			session.getTransaction().commit();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public User validate(String name, String pwd) throws Exception {
		Session session = util.getCurrentSession();
		
		try {
			session.beginTransaction();
			User valUser = new User();
			valUser.setName(name);
			valUser.setPassword(pwd);
			Example example = Example.create(valUser).excludeNone().excludeProperty("id");
			List<User> users = session.createCriteria(User.class).add(example).list();
			User user = new User();
			if(users.size()>0)
				user = users.get(0);
			else
				user = null;
			session.getTransaction().commit();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void delete(int id) throws Exception {
		Session session = util.getCurrentSession();
		
		try {
			session.beginTransaction();
			User user = (User)session.get(User.class, id);
			Set<Blog> blogs = user.getBlogs();
			for(Iterator<Blog> iter = blogs.iterator();iter.hasNext();){
				Blog blog = iter.next();
				blog.setAuthor(null);
			}
			user.setBlogs(null);
			session.delete(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

}
