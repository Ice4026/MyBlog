package com.myblog.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.myblog.dao.BlogDAO;
import com.myblog.dao.CommentDAO;
import com.myblog.dao.impl.BlogDAOImpl;
import com.myblog.dao.impl.CommentDAOImpl;
import com.myblog.model.Blog;
import com.myblog.model.Comment;
import com.myblog.util.CookieUtil;
import com.myblog.util.ValidateUtil;
import com.opensymphony.xwork2.ActionContext;

public class detailAction extends baseAction implements ServletRequestAware{
	private static final long serialVersionUID = -1428992624797431362L;
	// 公共字段
	private int blog_id;
	private Blog blog = new Blog();
	private List<Comment> comments;
	private String comment_name;

	private HttpServletRequest request;
	//私有字段
	private Map<String, Object> session;
	
	
	public String getComment_name() {
		return comment_name;
	}

	public void setComment_name(String comment_name) {
		this.comment_name = comment_name;
	}
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public detailAction(){
		session = ActionContext.getContext().getSession();
	}

	public int getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(int blog_id) {
		this.blog_id = blog_id;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public String execute() {
		
		try {
			BlogDAO d_blog = new BlogDAOImpl();
			blog = d_blog.get(blog_id);
			ValidateUtil validateUtil = new ValidateUtil();
			if(blog.getCategory().getPrivacy() == 0 && !validateUtil.loginValidate(session))
				return ERROR;
			CommentDAO d_comment = new CommentDAOImpl();
			comments = d_comment.commentList(blog_id);
			
			//读取cookie
			CookieUtil cookieUtil = new CookieUtil();
			comment_name = cookieUtil.getCookie("comment_name", request);
			
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
