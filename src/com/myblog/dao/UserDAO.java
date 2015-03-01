package com.myblog.dao;

import com.myblog.model.User;

public interface UserDAO {
	public User get(int id) throws Exception;
	public User validate(String name, String pwd) throws Exception; //验证用户名密码，若存在则返回User对象，否则返回null
	public void delete(int id) throws Exception;
}
