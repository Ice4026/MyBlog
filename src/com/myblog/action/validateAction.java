package com.myblog.action;

import java.util.Map;

import com.myblog.util.ValidateUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class validateAction extends ActionSupport{
	private static final long serialVersionUID = -155007994371398991L;
	private Map<String, Object> session;
	
	public validateAction(){
		session = ActionContext.getContext().getSession();
	}
	
	@Override
	public void validate(){
		ValidateUtil validateUtil = new ValidateUtil();
		if(!validateUtil.loginValidate(session)){
			this.addFieldError("login", "login failed");
		}
	}
}
