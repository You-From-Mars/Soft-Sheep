package com.helen.softsheep.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "overviews")
public class OverviewEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private String overviewUuid;
	private String userUuid;
	private String articleUuid;
	private String author;
	private String overviewContent;
	private String createdTime;
	private String title;
	private int pageView;
	private int starCount;
	private int commentCount;
	private String img;
	private long timer;
	public String getOverviewUuid() {
		return overviewUuid;
	}
	public void setOverviewUuid(String overviewUuid) {
		this.overviewUuid = overviewUuid;
	}
	public String getUserUuid() {
		return userUuid;
	}
	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getOverviewContent() {
		return overviewContent;
	}
	public void setOverviewContent(String overviewContent) {
		this.overviewContent = overviewContent;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public long getTimer() {
		return timer;
	}
	public void setTimer(long l) {
		this.timer = l;
	}
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
	public int getPageView() {
		return pageView;
	}
	public void setPageView(int pageView) {
		this.pageView = pageView;
	}
	public int getStarCount() {
		return starCount;
	}
	public void setStarCount(int starCount) {
		this.starCount = starCount;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
}
