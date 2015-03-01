package com.myblog.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.myblog.util.StrUtil;

@Entity
public class Blog {
	private int id;
	private String content; // 博文
	private String title;
	private Date publish_time;
	private Date update_time;
	private int count; // 访问次数
	private Set<Tag> tags = new HashSet<Tag>(); // 所携带标签，多对多
	private Category category;
	private User author;
	private Set<Comment> comments;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "blog", cascade = CascadeType.REMOVE)
	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	@ManyToOne
	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String briefContent() {
		String inputString;
		int len = 300;
		if (content.length() < len)
			inputString = content;
		else
			inputString = content.substring(0, len);
		return StrUtil.HtmlFilter(inputString);
	}

	// 返回格式化后的发布时间
	public String formatPT() {
		if (publish_time == null)
			return null;
		DateFormat df = new SimpleDateFormat("MMMM dd, yyyy  HH:mm", Locale.US);
		return df.format(publish_time);
	}

	// 返回格式化后的更新时间
	public String formatUT() {
		if (publish_time == null)
			return null;
		DateFormat df = new SimpleDateFormat("MMMM dd, yyyy  HH:mm", Locale.US);
		return df.format(update_time);
	}

	@ManyToOne
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Lob
	public String getContent() {
		return content;
	}

	public int getCount() {
		return count;
	}

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public Date getPublish_time() {
		return publish_time;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	/*
	 * @JoinTable( name="blog_tag", joinColumns=@JoinColumn(name="blogs_id"),
	 * inverseJoinColumns=@JoinColumn(name="tags_id") )
	 */
	public Set<Tag> getTags() {
		return tags;
	}

	public String getTitle() {
		return title;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPublish_time(Date publish_time) {
		this.publish_time = publish_time;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
}
