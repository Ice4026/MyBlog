package com.myblog.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import com.myblog.dao.CategoryDAO;
import com.myblog.model.Blog;
import com.myblog.model.Category;
import com.myblog.util.DBUtil;

public class CategoryDAOImpl implements CategoryDAO {
	private DBUtil util = new DBUtil();

	@Override
	public void create(Category category) throws Exception {
		Session session = util.getCurrentSession();

		try {
			session.beginTransaction();
			session.save(category);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void update(Category category) throws Exception {
		Session session = util.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(category);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void delete(int id) throws Exception {
		Session session = util.getCurrentSession();
		try {
			session.beginTransaction();
			Category category = (Category) session.get(Category.class, id);

			// 删除Category下所有Blog，删除前将blog的tags属性设为null，防止将tag一起删掉
			Set<Blog> blogs = category.getBlogs();
			for (Iterator<Blog> iter = blogs.iterator(); iter.hasNext();) {
				Blog b = (Blog) iter.next();
				b.setTags(null);
				b.setCategory(null);
				b.setAuthor(null);
				session.delete(b);
			}

			session.delete(category);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Category get(int id) throws Exception {
		Session session = util.getCurrentSession();

		try {
			session.beginTransaction();
			Category category = (Category) session.get(Category.class, id);
			session.getTransaction().commit();
			return category;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	// 返回列表按publish_time降序排列
	public List<Blog> getBlogs(Category category) throws Exception {
		Session session = util.getCurrentSession();

		try {
			session.beginTransaction();
			Query query = session
					.createQuery("from Blog as blog where blog.category=category.id order by blog.publish_time desc");
			List<Blog> list = query.list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Category> list() throws Exception {
		Session session = util.getCurrentSession();

		try {
			session.beginTransaction();
			Query query = session.createQuery("from Category");
			List<Category> list = query.list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
