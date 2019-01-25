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

import com.helen.softsheep.dao.CommentDao;
import com.helen.softsheep.entity.CommentEntity;

@RestController
public class CreateComment {
	@Autowired
	private CommentDao CommentDao;
	@RequestMapping(value = "/softsheep/comment")
	@ResponseBody
	public String index(HttpServletRequest req, @RequestBody Map<String, Object> params) throws Exception {
		HttpSession session = req.getSession();
		String commentUuid = UUID.randomUUID().toString().replaceAll("-", "");
		String articleUuid = (String) params.get("articleId");
		String userName = (String) session.getAttribute("userName");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createdTime = sdf.format(new Date());
		String commentContent = (String) params.get("content");
		CommentEntity comment = new CommentEntity();
		comment.setArticleUuid(articleUuid);
		comment.setCommentContent(commentContent);
		comment.setCommentUuid(commentUuid);
		comment.setCreatedTime(createdTime);
		comment.setUserName(userName);
		CommentDao.save(comment);
		return "保存成功";
	}
}
