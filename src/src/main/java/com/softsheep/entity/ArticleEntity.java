package com.softsheep.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "articles")
public class ArticleEntity implements Serializable {
	@Id
	private String articleUuid;
	private String title;
	private String content;
	private String userUuid;
	private String createdTime;
	private int pageView;
	public String getArticleUuid() {
		return articleUuid;
	}
	public void setArticleUuid(String articleUuid) {
		this.articleUuid = articleUuid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserUuid() {
		return userUuid;
	}
	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public int getPageView() {
		return pageView;
	}
	public void setPageView(int pageView) {
		this.pageView = pageView;
	}
}
