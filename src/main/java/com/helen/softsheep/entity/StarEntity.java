package com.helen.softsheep.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "stars")
public class StarEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String starId;
	private String userUuid;
	private String userName;
	private String articleUuid;
	public String getStarId() {
		return starId;
	}
	public void setStarId(String starId) {
		this.starId = starId;
	}
	public String getArticleUuid() {
		return articleUuid;
	}
	public void setArticleUuid(String articleUuid) {
		this.articleUuid = articleUuid;
	}
	public String getUserUuid() {
		return userUuid;
	}
	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
