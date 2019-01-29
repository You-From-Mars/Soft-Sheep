package com.helen.softsheep.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.helen.softsheep.dao.OverviewDao;
import com.helen.softsheep.entity.OverviewEntity;
import com.helen.softsheep.result.GenericResult;

@RestController
public class PersonalArticleList {
	@Autowired
	private OverviewDao overviewDao;
	@RequestMapping(value = "/softsheep/personal_articles")
	@ResponseBody
	public GenericResult<List<OverviewEntity>> index(HttpServletRequest req,HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("userUuid");
		return GenericResult.success(overviewDao.findOverviewsByUserId(userId));
	}
}
