package com.helen.softsheep.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.helen.softsheep.dao.ArticleDao;
import com.helen.softsheep.dao.OverviewDao;
import com.helen.softsheep.entity.ArticleEntity;
import com.helen.softsheep.entity.OverviewEntity;
import com.helen.softsheep.result.GenericResult;

@RestController
public class CreateArticle {
	
	public Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ArticleDao ArticleDao;
	@Autowired
	private OverviewDao OverviewDao;
	@RequestMapping(value = "/softsheep/article")
	@ResponseBody
	public GenericResult<String> index(HttpServletRequest req, @RequestBody Map<String, Object> params) throws Exception {
		HttpSession session = req.getSession();
		String userUuid = (String)session.getAttribute("userUuid");
		String title = (String) params.get("title");
		String author = (String)session.getAttribute("userName");
		String content = (String) params.get("content");
		String articleId = (String) params.get("id");
		String REGEX = "#*=*\\**`*\\+*>*-*\\[*\\]*\\s*\\(*\\)*(toc)*@*";
		String REPLACE = "";
		String overviewContent = "";
		if (content.length() > 50) {
			overviewContent = content.replaceAll(REGEX, REPLACE).substring(0, 50) + "...";
		} else {
			overviewContent = content.replaceAll(REGEX, REPLACE);
		}
		logger.info("overviewContent" + overviewContent);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createdTime = sdf.format(new Date());
		
		int pageView = 0;
		ArticleEntity _article = null;
		String articleUuid = "";
		String overviewUuid = "";
		OverviewEntity _overview = null;
		
		if (articleId.equals("")) {
			articleUuid = UUID.randomUUID().toString().replaceAll("-", "");
			_article = new ArticleEntity();
		} else {
			_article = ArticleDao.findArticleById(articleId);
			articleUuid = articleId;
		}
		_article.setArticleUuid(articleUuid);
		_article.setContent(content);
		_article.setCreatedTime(createdTime);
		_article.setPageView(pageView);
		_article.setTitle(title);
		_article.setUserUuid(userUuid);
		if (articleId.equals("")) {
			ArticleDao.saveArticle(_article);
			overviewUuid = UUID.randomUUID().toString().replaceAll("-", "");
			_overview = new OverviewEntity();
		} else {
			ArticleDao.updateArticle(_article);
			_overview = OverviewDao.findOverviewByArticleId(articleUuid);
		}
		_overview.setAuthor(author);
		_overview.setCreatedTime(createdTime);
		_overview.setOverviewContent(overviewContent);
		_overview.setOverviewUuid(overviewUuid);
		_overview.setTimer(new Date().getTime());
		_overview.setUserUuid(userUuid);
		_overview.setArticleUuid(articleUuid);
		_overview.setTitle(title);
		if (articleId.equals("")) {
			OverviewDao.save(_overview);
		} else {
			OverviewDao.update(_overview);
		}
		
		return GenericResult.success("创建成功");
	}
}
