package com.myblog.action;

import java.util.Date;
import java.util.Map;

import com.myblog.dao.BlogDAO;
import com.myblog.dao.CategoryDAO;
import com.myblog.dao.UserDAO;
import com.myblog.dao.impl.BlogDAOImpl;
import com.myblog.dao.impl.CategoryDAOImpl;
import com.myblog.dao.impl.UserDAOImpl;
import com.myblog.model.Blog;
import com.myblog.model.Category;
import com.myblog.model.User;
import com.opensymphony.xwork2.ActionContext;

/*
 * 新建或者更新博文的后续处理
 * */
public class blogAction extends validateAction {
	private static final long serialVersionUID = -2599756950705350721L;
	private String title;
	private int category_id;
	private String content;
	private int blog_id;
	
	private Map<String, Object> session;
	
	public blogAction(){
		session = ActionContext.getContext().getSession();
	}
	
	public int getBlog_id() {
		return blog_id;
	}

	public int getCategory() {
		return category_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public String getContent() {
		return content;
	}

	public String getTitle() {
		return title;
	}
	
	public String deleteBlog(){
		BlogDAO d_blog = new BlogDAOImpl();
		try{
			d_blog.delete(blog_id);
		}catch(Exception e){
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String newBlog() {
		BlogDAO d_blog = new BlogDAOImpl();
		CategoryDAO d_category = new CategoryDAOImpl();
		UserDAO d_user = new UserDAOImpl();
		try {
			Category ctgy = d_category.get(category_id);
			Blog blog = new Blog();
			User author = d_user.get((Integer)session.get("user_id"));
			blog.setTitle(title);
			blog.setCategory(ctgy);
			blog.setContent(content);
			blog.setPublish_time(new Date());
			blog.setUpdate_time(new Date());
			blog.setAuthor(author);
			
			d_blog.create(blog);
		} catch (Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}

	public void setBlog_id(int blog_id) {
		this.blog_id = blog_id;
	}

	public void setCategory(int category_id) {
		this.category_id = category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String updateBlog() {
		BlogDAO d_blog = new BlogDAOImpl();
		CategoryDAO d_category = new CategoryDAOImpl();
		try {
			Category ctgy = d_category.get(category_id);
			Blog blog = d_blog.get(blog_id);
			blog.setTitle(title);
			blog.setCategory(ctgy);
			blog.setContent(content);
			blog.setUpdate_time(new Date());

			d_blog.update(blog);
		} catch (Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}
}
