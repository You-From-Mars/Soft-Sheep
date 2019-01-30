package com.helen.softsheep.dao;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.helen.softsheep.entity.StarEntity;

@Component
public class StarDaoImpl implements StarDao{
	@Resource
	private MongoTemplate mongoTemplate;
	/**
     * 创建对象
     * @param star
     */
	@Override
	public void saveStar(StarEntity starEntity) {
		mongoTemplate.save(starEntity);
	}
	@Override
	public void removeStar(String articleUuid) {
		Query query = new Query(Criteria.where("articleUuid").is(articleUuid));
		mongoTemplate.remove(query, StarEntity.class);
	}
	/**
     * 创建对象
     * @param articleUuid
     */
	@Override
	public Boolean isStar(String articleUuid, String userUuid) {
		Query query = new Query(Criteria.where("articleUuid").is(articleUuid));
		query.addCriteria(Criteria.where("userUuid").is(userUuid));
		StarEntity star = mongoTemplate.findOne(query, StarEntity.class);
		System.out.println("star" + star);
		if (star == null) {
			return false;
		} else {
			return true;
		}
	}
}
