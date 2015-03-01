package com.myblog.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.myblog.dao.BlogDAO;
import com.myblog.model.Blog;
import com.myblog.util.DBUtil;

public class BlogDAOImpl implements BlogDAO {
	private DBUtil util = new DBUtil();

	@Override
	public void create(Blog blog) throws Exception {
		Session session = util.getCurrentSession();

		try {
			session.beginTransaction();
			session.save(blog);
			//category中加入blog，可以测试下不加该部分是否有效
			//猜测应该不需要，原因是category中没有对应的字段
			//Category category = blog.getCategory();
			//category.getBlogs().add(blog);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void update(Blog blog) throws Exception {
		Session session = util.getCurrentSession();
		try {
			//category表中无对应字段，因此猜测只要blog中更改即可
			session.beginTransaction();
			session.update(blog);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Blog get(int id) throws Exception {
		Session session = util.getCurrentSession();

		try {
			session.beginTransaction();
			Blog blog = (Blog) session.get(Blog.class, id);
			session.getTransaction().commit();
			return blog;
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
			Blog blog = (Blog)session.get(Blog.class, id);
//			Category category = blog.getCategory();
			//清理blog两边关系————无需清理Category方向
//			category.getBlogs().remove(blog);					//category中移除
			blog.setCategory(null);								//blog中category改为null
			blog.setTags(null);									//tags设为null
			blog.setAuthor(null);
			
			session.delete(blog);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Blog> list(int privacy) throws Exception {
		Session session = util.getCurrentSession();
		
		try {
			session.beginTransaction();
			String hql = "from Blog as blog";
			if(privacy == 0)
				hql += " where blog.category.privacy=0";
			else if(privacy == 1)
				hql += " where blog.category.privacy=1";
			hql += " order by blog.publish_time desc";
			Query query = session.createQuery(hql);
			List<Blog> list = query.list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public long count(int privacy) throws Exception {
		Session session = util.getCurrentSession();
		
		try {
			session.beginTransaction();
			String hql = "select count(*) from Blog as blog";
			if(privacy == 0)
				hql += " where blog.category.privacy=0";
			else if(privacy == 1)
				hql += " where blog.category.privacy=1";
			Query query = session.createQuery(hql);
			long count = ((Number)query.uniqueResult()).intValue();
			session.getTransaction().commit();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Blog> pagedList(int firstRet, int maxRet, int privacy) throws Exception {
		Session session = util.getCurrentSession();
		
		try {
			session.beginTransaction();
			String hql = "from Blog as blog";
			if(privacy == 0)
				hql += " where blog.category.privacy=0";
			else if(privacy == 1)
				hql += " where blog.category.privacy=1";
			hql += " order by blog.publish_time desc";
			Query query = session.createQuery(hql);
			query.setFirstResult(firstRet);
			query.setMaxResults(maxRet);
			List<Blog> list = query.list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
