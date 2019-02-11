package com.helen.softsheep.dao;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.helen.softsheep.entity.FollowEntity;

@Component
public class FollowDaoImpl implements FollowDao {
	@Resource
	private MongoTemplate mongoTemplate;

	@Override
	public void saveFollow(FollowEntity follow) {
		mongoTemplate.save(follow);
	}

	@Override
	public void removeFollow(FollowEntity follow) {
		Query query=new Query(Criteria.where("id").is(follow.getId()));
		query.addCriteria(Criteria.where("followId").is(follow.getFollowId()));
		mongoTemplate.remove(query,FollowEntity.class);
	}

}
