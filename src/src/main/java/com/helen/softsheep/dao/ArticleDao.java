package com.helen.softsheep.dao;
import java.util.List;

import com.helen.softsheep.entity.ArticleEntity;

public interface ArticleDao {
	void saveArticle(ArticleEntity article);
	ArticleEntity findArticleById(String id);
	List findArticlesByUserId(String id);
	List<ArticleEntity> getArticles();
	void updateArticle(ArticleEntity article);
	void deleteArticleById(String id);
}
