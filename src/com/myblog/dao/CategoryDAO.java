package com.myblog.dao;

import java.util.List;

import com.myblog.model.Blog;
import com.myblog.model.Category;

public interface CategoryDAO {
	public void create(Category category) throws Exception;							//新建Category
	public void update(Category category) throws Exception;							//更新Category
	public void delete(int id) throws Exception;							//刪除
	public Category get(int id) throws Exception;									//根據id獲取Category對象
	public List<Blog> getBlogs(Category category) throws Exception;
	public List<Category> list() throws Exception;									//獲取所有類別
}
