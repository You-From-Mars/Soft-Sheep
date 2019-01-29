package com.helen.softsheep.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;

import com.helen.softsheep.entity.OverviewEntity;
import com.helen.softsheep.response.OverviewBody;

public interface OverviewDao {
	void save(OverviewEntity overview);
	void update(OverviewEntity overview);
	OverviewBody findOverviews(int pageNum, int pageSize);
	OverviewEntity findOverviewByArticleId(String articleId);
	List<OverviewEntity> findOverviewsByUserId(String userId);
	void deleteOverviewById(String overviewId);
	Long getOverviewCount(Query query);
}
