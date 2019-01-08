package com.helen.softsheep.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.helen.softsheep.dao.ArticleDao;

@RestController
public class PersonalArticleList {
	@Autowired
	private ArticleDao ArticleDao;
	@RequestMapping(value = "/softsheep/personal_articles")
	@ResponseBody
	public List index(HttpServletRequest req,HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("userUuid");
		return ArticleDao.findArticlesByUserId(userId);
	}
}
