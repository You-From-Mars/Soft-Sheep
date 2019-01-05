package com.softsheep.entity;

import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class UserEntity implements Serializable {
	@Id
	private String userUuid;
	private String email;
	private String username;
	private int sex;  // 0: 男生，1: 女生
	public String getUserUuid() {
		return userUuid;
	}
	public void setUserUuid(String userUuid) {
		this.userUuid = userUuid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
}
