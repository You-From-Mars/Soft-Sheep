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
import com.helen.softsheep.entity.ArticleEntity;

//private String articleUuid;
//private String title;
//private String content;
//private String userUuid;
//private String createdTime;
//private int pageView;

@RestController
public class CreateArticle {
	@Autowired
	private ArticleDao ArticleDao;
	@RequestMapping(value = "/softsheep/create_article")
	@ResponseBody
	public String index(HttpServletRequest req, @RequestBody Map<String, Object> params) throws Exception {
		HttpSession session = req.getSession();
		String userUuid = (String)session.getAttribute("userUuid");
		String title = (String) params.get("title");
		String content = (String) params.get("content");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createdTime = sdf.format(new Date());
		System.out.println("createdTime======" + createdTime);
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
		return "创建成功";
	}
}
