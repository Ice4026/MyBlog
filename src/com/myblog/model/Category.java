package com.myblog.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	private int id;
	private String name;
	private Set<Blog> blogs = new HashSet<Blog>();		//一对多
	private int privacy = 0;  //0为不公开，1为公开
	
	public int getPrivacy() {
		return privacy;
	}
	public void setPrivacy(int privacy) {
		this.privacy = privacy;
	}
	@OneToMany(fetch=FetchType.LAZY, mappedBy="category")
	public Set<Blog> getBlogs() {
		return blogs;
	}
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setBlogs(Set<Blog> blogs) {
		this.blogs = blogs;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
}
