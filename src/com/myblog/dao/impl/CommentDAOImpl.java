package com.myblog.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.myblog.dao.BlogDAO;
import com.myblog.dao.CommentDAO;
import com.myblog.model.Blog;
import com.myblog.model.Comment;
import com.myblog.util.DBUtil;

public class CommentDAOImpl implements CommentDAO {
	private DBUtil util = new DBUtil();

	@Override
	public void create(Comment comment) throws Exception {
		Session session = util.getCurrentSession();

		try {
			session.beginTransaction();
			session.save(comment);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void update(Comment comment) throws Exception {
		Session session = util.getCurrentSession();
		try {
			session.beginTransaction();
			session.update(comment);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Comment get(int id) throws Exception {
		Session session = util.getCurrentSession();

		try {
			session.beginTransaction();
			Comment comment = (Comment) session.get(Comment.class, id);
			session.getTransaction().commit();
			return comment;
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
			Comment comment = (Comment) session.get(Comment.class, id);

			/*Blog blog = comment.getBlog();
			blog.getComments().remove(comment);*/
			comment.setBlog(null);

			session.delete(comment);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Comment> list(int privacy) throws Exception {
		Session session = util.getCurrentSession();

		try {
			session.beginTransaction();
			String hql = "from Comment as comment";
			if (privacy == 0)
				hql += " where comment.blog.category.privacy=0";
			else if (privacy == 1)
				hql += " where comment.blog.category.privacy=1";
			hql += " order by comment.time desc";
			Query query = session.createQuery(hql);
			List<Comment> list = query.list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public List<Comment> commentList(int blog_id) throws Exception {
		Session session = util.getCurrentSession();

		try {
			session.beginTransaction();
			String hql = "from Comment as comment where comment.blog.id=:blog_id order by comment.time desc";
			Query query = session.createQuery(hql).setParameter("blog_id", blog_id);
			List<Comment> list = query.list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
