package com.myblog.action;

import java.util.ArrayList;
import java.util.List;

import com.myblog.dao.BlogDAO;
import com.myblog.dao.CategoryDAO;
import com.myblog.dao.impl.BlogDAOImpl;
import com.myblog.dao.impl.CategoryDAOImpl;
import com.myblog.model.Blog;
import com.myblog.model.Category;

public class writerAction extends validateAction {
	private static final long serialVersionUID = 4300712810125559514L;
	private int blog_id;
	private Blog blog = new Blog();
	private int newOrNot = 0; // 0为新建，1为更新
	private List<Category> categories = new ArrayList<Category>();
	
	public String updateBlog(){
		try{
			newOrNot = 1;
			CategoryDAO d_category = new CategoryDAOImpl();
			BlogDAO d_blog = new BlogDAOImpl();
			
			categories = d_category.list();
			blog = d_blog.get(blog_id);
		}catch(Exception e){
			return ERROR;
		}
		
		return SUCCESS;
	}

	public String newBlog() {
		try {
			newOrNot = 0;
			CategoryDAO d_category = new CategoryDAOImpl();
			categories = d_category.list();
		} catch (Exception e) {
			return ERROR;
		}
		return SUCCESS;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public int getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(int blog_id) {
		this.blog_id = blog_id;
	}

	public int getNewOrNot() {
		return newOrNot;
	}

	public void setNewOrNot(int newOrNot) {
		this.newOrNot = newOrNot;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

}
