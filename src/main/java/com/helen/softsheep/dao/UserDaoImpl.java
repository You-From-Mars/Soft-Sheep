package com.helen.softsheep.dao;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import com.helen.softsheep.entity.UserEntity;

@Component
public class UserDaoImpl implements UserDao {
	@Resource
	private MongoTemplate mongoTemplate;
	/**
     * 创建对象
     * @param user
     */
	@Override
	public void saveUser(UserEntity user) {
		mongoTemplate.save(user);
	}
	/**
     * 根据id查询对象
     * @param id
     * @return
     */
	@Override
	public UserEntity findUserById(String id) {
		Query query = new Query(Criteria.where("userUuid").is(id));
		UserEntity user = mongoTemplate.findOne(query, UserEntity.class);
		return user;
	}
	/**
     * 根据email查询对象
     * @param email
     * @return
     */
	@Override
	public UserEntity findUserByEmail(String email) {
		Query query = new Query(Criteria.where("email").is(email));
		UserEntity user = mongoTemplate.findOne(query, UserEntity.class);
		return user;
	}
	
	/**
     * 创建对象
     * @param user
     */
	@Override
	public void updateUser(UserEntity user){
		Query query=new Query(Criteria.where("userUuid").is(user.getUserUuid()));
		Update update= new Update()
				.set("email", user.getEmail())
				.set("username", user.getUsername())
				.set("sex", user.getSex())
				.set("password", user.getPassword());
		mongoTemplate.updateFirst(query,update,UserEntity.class);
	}
	/**
     * 删除对象
     * @param userUuid
     */
	@Override
	public void deleteUserById(String id){
		Query query=new Query(Criteria.where("userUuid").is(id));
		mongoTemplate.remove(query,UserEntity.class);
	}
}
