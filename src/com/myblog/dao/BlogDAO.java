package com.myblog.dao;

import java.util.List;

import com.myblog.model.Blog;

public interface BlogDAO {
	public void create(Blog blog) throws Exception; // 新建blog

	public void update(Blog blog) throws Exception; // 更新blog

	public Blog get(int id) throws Exception; // 通過id獲取blog

	public void delete(int id) throws Exception; // 刪除blog

	public List<Blog> list(int privacy) throws Exception; // 獲取所有blog

	public long count(int privacy) throws Exception; // 获取blog总数

	/*
	 * @param privacy
	 * 0为不公开，1为公开，2为all
	 * */
	public List<Blog> pagedList(int firstRet, int maxRet, int privacy) throws Exception; // 获取分页后的blog列表
}
