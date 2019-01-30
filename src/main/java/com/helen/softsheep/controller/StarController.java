package com.helen.softsheep.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.helen.softsheep.dao.ArticleDao;
import com.helen.softsheep.dao.OverviewDao;
import com.helen.softsheep.dao.StarDao;
import com.helen.softsheep.entity.ArticleEntity;
import com.helen.softsheep.entity.OverviewEntity;
import com.helen.softsheep.entity.StarEntity;
import com.helen.softsheep.result.CommonCode;
import com.helen.softsheep.result.GenericResult;

@RestController
public class StarController {
	@Autowired 
	StarDao starDao;
	@Autowired 
	OverviewDao overviewDao;
	@Autowired 
	ArticleDao articleDao;
	@RequestMapping(value = "/softsheep/star")
	@ResponseBody
	public GenericResult<CommonCode> addStar(HttpServletRequest req, @RequestBody Map<String, Object> params) throws Exception {
		HttpSession session = req.getSession();
		String userUuid = (String) session.getAttribute("userUuid");
		if (userUuid == null) {
			return GenericResult.fail(CommonCode.CODE_NO_LOGIN);
		}
		String userName = (String) session.getAttribute("userName");
		String articleUuid = (String) params.get("articleUuid");
//		更新overviews表中的starCount字段
		this.update("star", articleUuid);
		StarEntity star = new StarEntity();
		star.setArticleUuid(articleUuid);
		star.setUserName(userName);
		star.setUserUuid(userUuid);
		star.setStarId(articleUuid + userUuid);
		starDao.saveStar(star);
		return GenericResult.success(CommonCode.CODE_SUCCESS);
	}
	
	private void update(String type, String articleUuid) {
		OverviewEntity _overview = overviewDao.findOverviewByArticleId(articleUuid);
		ArticleEntity _article = articleDao.findArticleById(articleUuid);
		int starCount = _overview.getStarCount();
		if (type == "star") {
			starCount++;
		} else {
			starCount--;
		}
		_overview.setStarCount(starCount);
		_article.setStarCount(starCount);
		overviewDao.update(_overview);
		articleDao.updateArticle(_article);
	}

	@RequestMapping(value = "/softsheep/removestar")
	@ResponseBody
	public GenericResult<CommonCode> removeStar(HttpServletRequest req, @RequestBody Map<String, Object> params) throws Exception {
		String articleUuid = (String) params.get("articleUuid");
		starDao.removeStar(articleUuid);
//		更新overviews表中的starCount字段
		this.update("removeStar", articleUuid);
		return GenericResult.success(CommonCode.CODE_SUCCESS);
	}
	@RequestMapping(value = "/softsheep/isstar")
	@ResponseBody
	public GenericResult<Boolean> getIsStar(HttpServletRequest req,HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		String userUuid = (String) session.getAttribute("userUuid");
		String articleUuid = req.getParameter("articleUuid");
		return GenericResult.success(starDao.isStar(articleUuid, userUuid));
	}
}
