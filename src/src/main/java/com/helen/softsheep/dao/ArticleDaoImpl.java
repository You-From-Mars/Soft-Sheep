package com.helen.softsheep.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.helen.softsheep.entity.ArticleEntity;
import com.helen.softsheep.response.ArticleBody;
@Component
public class ArticleDaoImpl implements ArticleDao {
	@Resource
	private MongoTemplate mongoTemplate;
	@Override
	public ArticleBody getArticles(int pageNum, int pageSize) {
		Query query = new Query();
		int skip = (pageNum - 1) * pageSize;
		query.skip(skip);
		query.limit(pageSize);
		List<ArticleEntity> articles = mongoTemplate.find(query, ArticleEntity.class);
		ArticleBody _articles = new ArticleBody();
		_articles.pageSize = pageSize;
		_articles.pageNo = pageNum;
		_articles.totalRecords = this.getArticleCount(new Query());
		_articles.totalPage = Math.ceil(_articles.totalRecords / pageSize);
		System.out.println("pageSize-----" + _articles.pageSize);
		System.out.println("totleRecords-----" + _articles.totalRecords);
		System.out.println("totalPage-----" + _articles.totalPage);
		_articles.articles = articles;
		return _articles;
	}
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
	@Override
	public Long getArticleCount(Query query) {
		long count= mongoTemplate.count(query, ArticleEntity.class);
		return count;
	}
}
