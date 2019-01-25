package com.helen.softsheep.dao;
import com.helen.softsheep.entity.UserEntity;

public interface UserDao {
	void saveUser(UserEntity user);
	UserEntity findUserByEmail(String email);
	UserEntity findUserById(String id);
	void updateUser(UserEntity user);
	void deleteUserById(String id);
}
