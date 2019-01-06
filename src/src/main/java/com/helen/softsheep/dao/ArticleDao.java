package com.helen.softsheep.dao;
import com.helen.softsheep.entity.ArticleEntity;

public interface ArticleDao {
	void saveArticle(ArticleEntity article);
	ArticleEntity findArticleById(String id);
	void updateArticle(ArticleEntity article);
	void deleteArticleById(String id);
}
