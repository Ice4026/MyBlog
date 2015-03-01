package com.myblog.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.myblog.dao.BlogDAO;
import com.myblog.dao.impl.BlogDAOImpl;
import com.myblog.model.Blog;
import com.myblog.util.ValidateUtil;
import com.opensymphony.xwork2.ActionContext;

public class indexAction extends baseAction {
	private static final long serialVersionUID = 4641958181461573821L;
	// 公共字段
	private List<Blog> blogs = new ArrayList<Blog>();
	private int page = 1;
	private List<Integer> pageNav = new ArrayList<Integer>();
	// 私有字段
	private int maxRet = 10;
	private BlogDAO d_blog = new BlogDAOImpl();
	private Map<String, Object> session;
	private boolean isLogin = false;
	private long count = 0;
	
	public indexAction() throws Exception{
		session = ActionContext.getContext().getSession();
		isLogin();
	}

	private void isLogin() throws Exception{
		ValidateUtil validateUtil = new ValidateUtil();
		if(validateUtil.loginValidate(session))
			isLogin = true;
		//统计博文总数
		if(isLogin)
			count = d_blog.count(2);
		else
			count = d_blog.count(1);
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<Integer> getPageNav() {
		return pageNav;
	}

	public void setPageNav(List<Integer> pageNav) {
		this.pageNav = pageNav;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	/*
	 * 生成长度为7的pageNav，用来配置分页导航条 0和6标识前进和后退按钮，值为-1则禁用该按钮
	 */
	private void generatePageNav() throws Exception {
		// 计算总页数
		long pageCount = (count - 1) / (long) maxRet + 1;
		// pageNav预处理
		int right = page + 2;
		for (int i = page - 2; i <= right; i++) {
			if (i < 1) {
				right++;
				continue;
			}
			if (i > pageCount) {
				pageNav.add(0, pageNav.get(0) - 1);
			} else {
				pageNav.add(i);
			}
		}
		// 筛掉不合条件的值
		for (int i = 0; i < pageNav.size(); i++) {
			int n = pageNav.get(i);
			if (n < 1 || n > pageCount) {
				pageNav.remove(i);
				i--;
			}
		}
		// 添上头尾标识符
		if (page == 1) {
			pageNav.add(0, -1);
		} else {
			pageNav.add(0, 0);
		}
		if (page == pageCount) {
			pageNav.add(-1);
		} else {
			pageNav.add(0);
		}
	}

	private void generateBlogList() throws Exception {
		if (page < 1 || page > count % (long) maxRet + 1)
			throw new Exception("Page out of range!");
		if(isLogin)
			blogs = d_blog.pagedList((page - 1) * maxRet, maxRet, 2);
		else
			blogs = d_blog.pagedList((page - 1) * maxRet, maxRet, 1);
	}

	public String execute() {
		try {
			generateBlogList();
			generatePageNav();

			return SUCCESS;
		} catch (Exception e) {
			return "debug";
		}
	}

}
