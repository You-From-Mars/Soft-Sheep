package com.helen.softsheep.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.helen.softsheep.response.OverviewBody;
import com.helen.softsheep.result.CommonCode;
import com.helen.softsheep.result.GenericResult;

@RestController
public class ArticleController {

	public Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ArticleDao articleDao;

	@Autowired
	private OverviewDao overviewDao;

	@RequestMapping(value = "/softsheep/article_detail")
	@ResponseBody
	public GenericResult<ArticleEntity> articleById(HttpServletRequest req) throws Exception {
		HttpSession session = req.getSession();
		String _userUuid = (String) session.getAttribute("userUuid");
		String articleId = req.getParameter("articleId");
		ArticleEntity article = articleDao.findArticleById(articleId);
		OverviewEntity overview = overviewDao.findOverviewByArticleId(articleId);
		if (_userUuid != null && _userUuid.equals(article.getUserUuid())) {
			article.setIsSelf(1);
		} else {
			article.setIsSelf(0);
		}
//		更新浏览量
		int pageView = article.getPageView();
		pageView++;
		article.setPageView(pageView);
		overview.setPageView(pageView);
		articleDao.saveArticle(article);
		overviewDao.save(overview);
		return GenericResult.success(article);
	}

	@RequestMapping(value = "/softsheep/articlelist")
	@ResponseBody
	public GenericResult<OverviewBody> articleList(HttpServletRequest req) throws Exception {
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		int pageSize = Integer.parseInt(req.getParameter("pageSize"));
		return GenericResult.success(overviewDao.findOverviews(pageNum, pageSize));
	}

	@RequestMapping(value = "/softsheep/article")
	@ResponseBody
	public GenericResult<String> saveArticle(HttpServletRequest req, @RequestBody Map<String, Object> params)
			throws Exception {
		HttpSession session = req.getSession();
		String userUuid = (String) session.getAttribute("userUuid");
		if (userUuid == null) {
			return GenericResult.fail(CommonCode.CODE_NO_LOGIN);
		}
		String title = (String) params.get("title");
		String author = (String) session.getAttribute("userName");
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
			_article.setStarCount(0);
		} else {
			_article = articleDao.findArticleById(articleId);
			articleUuid = articleId;
		}
		_article.setArticleUuid(articleUuid);
		_article.setContent(content);
		_article.setCreatedTime(createdTime);
		_article.setPageView(pageView);
		_article.setTitle(title);
		_article.setUserUuid(userUuid);
		if (articleId.equals("")) {
			articleDao.saveArticle(_article);
			overviewUuid = UUID.randomUUID().toString().replaceAll("-", "");
			_overview = new OverviewEntity();
			_overview.setStarCount(0);
		} else {
			articleDao.updateArticle(_article);
			_overview = overviewDao.findOverviewByArticleId(articleUuid);
		}
		_overview.setAuthor(author);
		_overview.setCreatedTime(createdTime);
		_overview.setOverviewContent(overviewContent);
		_overview.setOverviewUuid(overviewUuid);
		_overview.setTimer(new Date().getTime());
		_overview.setUserUuid(userUuid);
		_overview.setArticleUuid(articleUuid);
		_overview.setPageView(pageView);
		_overview.setTitle(title);
		if (articleId.equals("")) {
			overviewDao.save(_overview);
		} else {
			overviewDao.update(_overview);
		}

		return GenericResult.success(_article.getArticleUuid());
	}

	@RequestMapping(value = "/softsheep/personal_articles")
	@ResponseBody
	public GenericResult<List<OverviewEntity>> articledByPerson(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("userUuid");
		return GenericResult.success(overviewDao.findOverviewsByUserId(userId));
	}
}
