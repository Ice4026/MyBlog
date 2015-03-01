package com.myblog.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.myblog.model.User;
import com.myblog.util.ValidateUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class baseAction extends ActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = -2122938444743609305L;
	private List<String> recentMonth = new ArrayList<String>();
	
	private Map<String, Object> session;
	private HttpServletRequest request;

	public List<String> getRecentMonth() {
		return recentMonth;
	}

	public void setRecentMonth(List<String> recentMonth) {
		this.recentMonth = recentMonth;
	}

	//生成最近12个月的列表
	private void generateMonthList() {
		DateFormat df = new SimpleDateFormat("MMMM yyyy", Locale.US);
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		for (int i = 0; i < 12; i++) {
			recentMonth.add(df.format(cal.getTime()));
			cal.add(Calendar.MONTH, -1);
		}
	}
	//获取cookie
	public void validate(){
		ValidateUtil validateUtil = new ValidateUtil();
		try {
			if(!validateUtil.loginValidate(session)){
				User user = validateUtil.getUserCookie(request);
				if(user != null){
					validateUtil.userValidate(user.getName(), user.getPassword(), session);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public baseAction() {
		super();
		
		session = ActionContext.getContext().getSession();
		generateMonthList();
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
}
