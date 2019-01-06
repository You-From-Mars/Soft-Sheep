package com.helen.softsheep.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
public class CommentEntity implements Serializable {
	@Id
	private String commentUuid;
	private String articleUuid;
	private String userName;
	private String createdTime;
	private String commentContent;
	public String getCommentUuid() {
		return commentUuid;
	}
	public void setCommentUuid(String commentUuid) {
		this.commentUuid = commentUuid;
	}
	public String getArticleUuid() {
		return articleUuid;
	}
	public void setArticleUuid(String articleUuid) {
		this.articleUuid = articleUuid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	
	
}
