package com.myblog.util;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myblog.dao.UserDAO;
import com.myblog.dao.impl.UserDAOImpl;
import com.myblog.model.User;

public class ValidateUtil {
	private String USER_COOKIE = "userCookie";
	/*
	 * 验证用户是否登录
	 * */
	public boolean loginValidate(Map<String, Object> session){
		Integer user_id = (Integer)session.get("user_id");
		if(user_id != null && user_id > 0)
			return true;
		return false;
	}
	
	/*
	 * 验证用户名密码并放到session
	 * */
	public boolean userValidate(String name, String password, Map<String, Object>session) throws Exception{
		UserDAO d_user = new UserDAOImpl();
		User user = d_user.validate(name, password);
		if(user!=null){
			session.put("user_id", user.getId());
			session.put("user_name", user.getName());
			return true;
		}
		return false;
	}
	
	public void rememberPassword(HttpServletResponse response, User user) throws UnsupportedEncodingException{
		CookieUtil cookieUtil = new CookieUtil();
		cookieUtil.addCookie(USER_COOKIE, user.getName() + "," + user.getPassword(), response);
	}
	
	public User getUserCookie(HttpServletRequest request) throws UnsupportedEncodingException{
		CookieUtil cookieUtil = new CookieUtil();
		
		String[] userInfo = cookieUtil.getCookie(USER_COOKIE, request).split(",");
		User user = new User();
		user.setName(userInfo[0]);
		user.setPassword(userInfo[1]);
		return user;
	}
	
	public void removeUserCookie(HttpServletRequest request, HttpServletResponse response){
		Cookie[] cs = request.getCookies();
		
		if (cs != null && cs.length > 0) {
			for (int i = 0; i < cs.length; i++) {
				Cookie c = cs[i];
				if(c.getName().equals(USER_COOKIE)){
					c.setValue("");
					c.setMaxAge(0);
					c.setPath("/");
					response.addCookie(c);
					return;
				}
			}
		}
	}
}
