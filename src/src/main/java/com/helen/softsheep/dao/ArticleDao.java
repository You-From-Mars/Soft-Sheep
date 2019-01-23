package com.helen.softsheep.dao;
import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import com.helen.softsheep.entity.ArticleEntity;
import com.helen.softsheep.response.ArticleBody;

public interface ArticleDao {
	void saveArticle(ArticleEntity article);
	ArticleEntity findArticleById(String id);
	List findArticlesByUserId(String id);
	ArticleBody getArticles(int pageNum, int pageSize);
	Long getArticleCount(Query query);
	void updateArticle(ArticleEntity article);
	void deleteArticleById(String id);
}
