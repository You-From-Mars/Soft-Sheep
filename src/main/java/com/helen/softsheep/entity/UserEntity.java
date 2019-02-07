package com.helen.softsheep.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.helen.softsheep.response.FollowType;

@Document(collection = "users")
public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private String userUuid;
	private String email;
	private String username;
	private String sex;  // 0: 男生，1: 女生
	private String password;
	private List<FollowType> follower = new ArrayList<FollowType>();
	private List<FollowType> following = new ArrayList<FollowType>();
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List getFollower() {
		return follower;
	}
	public void setFollower(FollowType follower) {
		this.follower.add(follower);
		System.out.println("this.follower" + this.follower);
	}
	public List getFollowing() {
		return following;
	}
	public void setFollowing(FollowType following) {
		this.following.add(following);
		System.out.println("this.following" + this.following);
	}
}
