package com.helen.softsheep.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.helen.softsheep.dao.OverviewDao;
import com.helen.softsheep.response.OverviewBody;
import com.helen.softsheep.result.GenericResult;

@RestController
public class Articlelist {
	@Autowired
	private OverviewDao OverviewDao;
	@RequestMapping(value = "/softsheep/articlelist")
	@ResponseBody
	public GenericResult<OverviewBody> index(HttpServletRequest req) throws Exception {
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		int pageSize = Integer.parseInt(req.getParameter("pageSize"));
		return GenericResult.success(OverviewDao.findOverviews(pageNum, pageSize));
	}
}
