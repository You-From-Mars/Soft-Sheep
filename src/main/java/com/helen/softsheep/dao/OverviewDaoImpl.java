package com.helen.softsheep.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.helen.softsheep.entity.CommentEntity;
import com.helen.softsheep.entity.OverviewEntity;
import com.helen.softsheep.response.OverviewBody;

@Component
public class OverviewDaoImpl implements OverviewDao  {
	@Resource
	private MongoTemplate mongoTemplate;

	@Override
	public void save(OverviewEntity overview) {
		mongoTemplate.save(overview);
	}
	
	@Override 
	public void update(OverviewEntity overview) {
		Query query=new Query(Criteria.where("articleUuid").is(overview.getArticleUuid()));
		Update update= new Update()
						.set("overviewContent", overview.getOverviewContent())
						.set("title", overview.getTitle())
						.set("starCount", overview.getStarCount())
						.set("timer", overview.getTimer())
						.set("createdTime", overview.getCreatedTime())
						.set("pageView", overview.getPageView());
		mongoTemplate.updateFirst(query, update, OverviewEntity.class);
	}

	@Override
	public OverviewBody findOverviews(int pageNum, int pageSize) {
		Query query = new Query();
		int skip = (pageNum - 1) * pageSize;
		query.skip(skip);
		query.limit(pageSize);
		List<OverviewEntity> overviews = mongoTemplate.find(query, OverviewEntity.class);
		OverviewBody _overviews = new OverviewBody();
		_overviews.pageSize = pageSize;
		_overviews.pageNo = pageNum;
		_overviews.totalRecords = this.getOverviewCount(new Query());
		_overviews.totalPage = Math.ceil(_overviews.totalRecords / pageSize);
		_overviews.overviews = overviews;
		return _overviews;
	}
	
	@Override
	public OverviewEntity findOverviewByArticleId(String articleId) {
		Query query = new Query(Criteria.where("articleUuid").is(articleId));
		return mongoTemplate.findOne(query, OverviewEntity.class);
	}
	
	@Override
	public Long getOverviewCount(Query query) {
		long count= mongoTemplate.count(query, OverviewEntity.class);
		return count;
	}

	@Override
	public List<OverviewEntity> findOverviewsByUserId(String userId) {
		Query query = new Query(Criteria.where("userUuid").is(userId));
		List<OverviewEntity> overviews = mongoTemplate.find(query, OverviewEntity.class);
		return overviews;
	}

	@Override
	public void deleteOverviewById(String overviewId) {
		Query query=new Query(Criteria.where("overviewUuid").is(overviewId));
		mongoTemplate.remove(query,CommentEntity.class);
	}
}
