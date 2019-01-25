package com.helen.softsheep.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.helen.softsheep.dao.ArticleDao;
import com.helen.softsheep.entity.ArticleEntity;

@RestController
public class ArticleDetail {
	@Autowired
	private ArticleDao ArticleDao;
	@RequestMapping(value = "/softsheep/article_detail")
	@ResponseBody
	public ArticleEntity index(HttpServletRequest req,HttpServletResponse res) throws Exception {
		String articleId = req.getParameter("articleId");
		ArticleEntity article = ArticleDao.findArticleById(articleId);
//		更新浏览量
		int pageView = article.getPageView();
		pageView++;
		article.setPageView(pageView);
		ArticleDao.saveArticle(article);
		return article;
	}
}
