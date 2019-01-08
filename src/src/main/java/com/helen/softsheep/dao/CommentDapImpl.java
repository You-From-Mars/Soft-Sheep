package com.helen.softsheep.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.helen.softsheep.entity.CommentEntity;
@Component
public class CommentDapImpl implements CommentDao {
	@Resource
	private MongoTemplate mongoTemplate;
	/**
     * 创建对象
     * @param comment
     */
	@Override
	public void save(CommentEntity comment) {
		mongoTemplate.save(comment);
	}
	@Override
	public List findCommentsById(String articleId) {
		Query query=new Query(Criteria.where("articleUuid").is(articleId));
		return mongoTemplate.find(query, CommentEntity.class);
	}
	/**
     * 删除
     * @param commentId
     */
	@Override
	public void deleteCommentById(String id) {
		Query query=new Query(Criteria.where("commentUuid").is(id));
		mongoTemplate.remove(query,CommentEntity.class);
	}
}
