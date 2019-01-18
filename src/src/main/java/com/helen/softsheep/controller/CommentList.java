package com.helen.softsheep.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.helen.softsheep.dao.CommentDao;
import com.helen.softsheep.entity.CommentEntity;

@RestController
public class CommentList {
	@Autowired
	private CommentDao CommentDao;
	@RequestMapping(value = "/softsheep/commentlist")
	@ResponseBody
	public List<CommentEntity> index(HttpServletRequest req,HttpServletResponse res) throws Exception {
		String articleId = req.getParameter("articleId");
		return CommentDao.findCommentsById(articleId);
	}
}

