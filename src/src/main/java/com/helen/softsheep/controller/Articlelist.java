package com.helen.softsheep.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.helen.softsheep.dao.ArticleDao;
import com.helen.softsheep.entity.ArticleEntity;
import com.helen.softsheep.response.ArticleBody;

@RestController
public class Articlelist {
	@Autowired
	private ArticleDao ArticleDao;
	@RequestMapping(value = "/softsheep/articlelist")
	@ResponseBody
	public ArticleBody index(HttpServletRequest req) throws Exception {
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		int pageSize = Integer.parseInt(req.getParameter("pageSize"));
		return ArticleDao.getArticles(pageNum, pageSize);
	}
}
