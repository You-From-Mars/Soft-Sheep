package com.helen.softsheep.dao;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.helen.softsheep.entity.ArticleEntity;
import com.helen.softsheep.entity.UserEntity;
@Component
public class ArticleDaoImpl implements ArticleDao {
	@Resource
	private MongoTemplate mongoTemplate;
	/**
     * 保存文章
     * @param ArticleEntity
     */
	@Override
	public void saveArticle(ArticleEntity article) {
		mongoTemplate.save(article);
	}
	/**
     * 根据id查询对象
     * @param id
     * @return
     */
	@Override
	public ArticleEntity findArticleById(String id) {
		Query query = new Query(Criteria.where("articleUuid").is(id));
		ArticleEntity article = mongoTemplate.findOne(query, ArticleEntity.class);
		return article;
	}
	/**
     * 根据userid查询对象
     * @param userUuid
     * @return
     */
	@Override
	public List findArticlesByUserId(String id) {
		Query query = new Query(Criteria.where("userUuid").is(id));
		List articles = mongoTemplate.find(query, ArticleEntity.class);
		return articles;
	}
	/**
     * 更新文章 
     * @param id
     * @return
     */
	@Override
	public void updateArticle(ArticleEntity article) {
		Query query=new Query(Criteria.where("articleUuid").is(article.getArticleUuid()));
		Update update= new Update()
				.set("title", article.getTitle())
				.set("content", article.getContent())
				.set("pageView", article.getPageView());
		mongoTemplate.updateFirst(query,update,ArticleEntity.class);
	}
	/**
     * 删除文章 
     * @param id
     * @return
     */
	@Override
	public void deleteArticleById(String id) {
		Query query=new Query(Criteria.where("articleUuid").is(id));
		mongoTemplate.remove(query,ArticleEntity.class);
	}
}
