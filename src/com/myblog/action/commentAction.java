package com.myblog.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;

import com.myblog.dao.CommentDAO;
import com.myblog.dao.impl.CommentDAOImpl;
import com.myblog.model.Blog;
import com.myblog.model.Comment;
import com.myblog.util.CookieUtil;
import com.opensymphony.xwork2.ActionSupport;

public class commentAction extends ActionSupport implements ServletResponseAware{
	private static final long serialVersionUID = 5636564220375012800L;
	//公共字段
	private String comment_name;
	private String comment_content;
	private int blog_id;
	private List<Comment> comments;
	
	private HttpServletResponse response;
	
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public int getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(int blog_id) {
		this.blog_id = blog_id;
	}
	public String getComment_name() {
		return comment_name;
	}
	public void setComment_name(String comment_name) {
		this.comment_name = comment_name;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public String execute(){
		CommentDAO d_comment = new CommentDAOImpl();
		try {
			Blog blog = new Blog();
			blog.setId(blog_id);
			Comment comment = new Comment();
			comment.setName(comment_name);
			comment.setContent(comment_content);
			comment.setTime(new Date());
			comment.setBlog(blog);
			d_comment.create(comment);
			comments = d_comment.commentList(blog_id);
			
			CookieUtil cookieUtil = new CookieUtil();
			cookieUtil.addCookie("comment_name", comment_name, response);
			
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
}
