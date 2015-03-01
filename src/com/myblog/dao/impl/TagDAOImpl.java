package com.myblog.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.myblog.dao.TagDAO;
import com.myblog.model.Tag;
import com.myblog.util.DBUtil;

public class TagDAOImpl implements TagDAO {
	private DBUtil util = new DBUtil();

	@Override
	public void create(Tag tag) throws Exception {
		Session session = util.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(tag);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void update(Tag tag) throws Exception {
		Session session = util.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(tag);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Tag get(int id) throws Exception {
		Session session = util.getCurrentSession();
		try {
			session.beginTransaction();
			Tag tag = (Tag) session.get(Tag.class, id);
			session.getTransaction().commit();
			return tag;
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
			Tag tag = (Tag) session.get(Tag.class, id);
			/*
			 * //先获取所有拥有该tag的blog对象，将tag从其属性中移除以后再删除 Set<Blog> blogs =
			 * tag.getBlogs(); for(Iterator<Blog> iter =
			 * blogs.iterator();iter.hasNext();){ Blog blog = (Blog)iter.next();
			 * blog.getTags().remove(tag); }
			 */
			// 直接使用SQL从中间表中删除关联记录
			Query query = session.createSQLQuery(
					"delete from blog_tag where tags_id=:id").setParameter(
					"id", id);
			query.executeUpdate();
			session.delete(tag);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Tag> list() throws Exception {
		Session session = util.getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery("from Tag");
			List<Tag> list = query.list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
