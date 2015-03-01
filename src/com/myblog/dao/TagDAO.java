package com.myblog.dao;

import java.util.List;

import com.myblog.model.Tag;

public interface TagDAO {
	public void create(Tag tag) throws Exception;
	public void update(Tag tag) throws Exception;
	public Tag get(int id) throws Exception;
	public void delete(int id) throws Exception;
	public List<Tag> list() throws Exception;
}
