package com.myblog.dao;

import java.util.List;

import com.myblog.model.Blog;
import com.myblog.model.Comment;

public interface CommentDAO {
	public void create(Comment comment) throws Exception;	//新建Comment
	
	public void update(Comment comment) throws Exception; // 更新Comment

	public Comment get(int id) throws Exception; // 通過id獲取Comment

	public void delete(int id) throws Exception; // 刪除Comment

	public List<Comment> list(int privacy) throws Exception; // 獲取所有Comment
	
	public List<Comment> commentList(int blog_id) throws Exception; //获取对应博客的评论
	
	/*
	 * @param privacy
	 * 0为不公开，1为公开，2为all
	 * */
	public List<Comment> pagedList(int firstRet, int maxRet, int privacy) throws Exception; // 获取分页后的comment列表
}
