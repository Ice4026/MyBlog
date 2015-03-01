package com.myblog.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.myblog.model.User;
import com.myblog.util.ValidateUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class loginAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{
	private static final long serialVersionUID = 8757416823261374196L;
	private String name;
	private String password;
	private Map<String, Object> session;
	private String login = "fail";
	private int rememberPw;
	
	private HttpServletRequest request;
	private HttpServletResponse response;

	public int getRememberPw() {
		return rememberPw;
	}

	public void setRememberPw(int rememberPw) {
		this.rememberPw = rememberPw;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String logout(){
		try{
			session.remove("user_id");
			session.remove("user_name");
			
			//清除cookie
			ValidateUtil validateUtil = new ValidateUtil();
			validateUtil.removeUserCookie(request, response);
		}catch(Exception e){
			return ERROR;
		}
		return SUCCESS;
	}
	
	@Override
	public String execute() throws Exception{
		ValidateUtil validateUtil = new ValidateUtil();
		if(validateUtil.userValidate(name, password, session)){
			if(rememberPw == 1){
				User user = new User();
				user.setName(name);
				user.setPassword(password);
				validateUtil.rememberPassword(response, user);
			}
			login = "success";
			return SUCCESS;
		}
		return ERROR;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public loginAction(){
		session = ActionContext.getContext().getSession();
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
}
