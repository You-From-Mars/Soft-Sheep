package com.helen.softsheep.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.helen.softsheep.dao.ArticleDao;
import com.helen.softsheep.dao.OverviewDao;
import com.helen.softsheep.entity.ArticleEntity;
import com.helen.softsheep.entity.OverviewEntity;

@RestController
public class CreateArticle {
	@Autowired
	private ArticleDao ArticleDao;
	@Autowired
	private OverviewDao OverviewDao;
	@RequestMapping(value = "/softsheep/article")
	@ResponseBody
	public String index(HttpServletRequest req, @RequestBody Map<String, Object> params) throws Exception {
		HttpSession session = req.getSession();
		String userUuid = (String)session.getAttribute("userUuid");
		String title = (String) params.get("title");
		String author = (String)session.getAttribute("userName");
		String content = (String) params.get("content");
		String REGEX = "#*=*\\**`*\\+*>*-*\\[*\\]*\\s*\\(*\\)*(toc)*@*";
		String REPLACE = "";
		String overviewContent = content.replaceAll(REGEX, REPLACE).substring(0, 50);
		System.out.println("overviewContent" + overviewContent);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createdTime = sdf.format(new Date());
		int pageView = 0;
		String articleUuid = UUID.randomUUID().toString().replaceAll("-", "");
		ArticleEntity _article = new ArticleEntity();
		_article.setArticleUuid(articleUuid);
		_article.setContent(content);
		_article.setCreatedTime(createdTime);
		_article.setPageView(pageView);
		_article.setTitle(title);
		_article.setUserUuid(userUuid);
		ArticleDao.saveArticle(_article);
		
		String overviewUuid = UUID.randomUUID().toString().replaceAll("-", "");
		OverviewEntity _overview = new OverviewEntity();
		_overview.setAuthor(author);
		_overview.setCreatedTime(createdTime);
		_overview.setOverviewContent(overviewContent);
		_overview.setOverviewUuid(overviewUuid);
		_overview.setTimer(new Date().getTime());
		_overview.setUserUuid(userUuid);
		_overview.setArticleUuid(articleUuid);
		_overview.setTitle(title);
		OverviewDao.save(_overview);
		return "创建成功";
	}
}
