package com.helen.softsheep.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.helen.softsheep.dao.CommentDao;
import com.helen.softsheep.entity.CommentEntity;
import com.helen.softsheep.result.CommonCode;
import com.helen.softsheep.result.GenericResult;

@RestController
public class CommentController {
	
	@Autowired
	private CommentDao commentDao;	
	
	@RequestMapping(value = "/softsheep/commentlist")
	@ResponseBody
	public GenericResult<List<CommentEntity>> commentsByArticleId(HttpServletRequest req,HttpServletResponse res) throws Exception {
		String articleId = req.getParameter("articleId");
		return GenericResult.success(commentDao.findCommentsById(articleId));
	}
	
	@RequestMapping(value = "/softsheep/comment")
	@ResponseBody
	public GenericResult<CommonCode> index(HttpServletRequest req, @RequestBody Map<String, Object> params) throws Exception {
		HttpSession session = req.getSession();
		String userName = (String) session.getAttribute("userName");
		if (userName == null) {
			return GenericResult.fail(CommonCode.CODE_NO_LOGIN);
		}
		String commentUuid = UUID.randomUUID().toString().replaceAll("-", "");
		String articleUuid = (String) params.get("articleId");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String createdTime = sdf.format(new Date());
		String commentContent = (String) params.get("content");
		CommentEntity comment = new CommentEntity();
		comment.setArticleUuid(articleUuid);
		comment.setCommentContent(commentContent);
		comment.setCommentUuid(commentUuid);
		comment.setCreatedTime(createdTime);
		comment.setUserName(userName);
		commentDao.save(comment);
		return GenericResult.success(CommonCode.CODE_SUCCESS);
	}
}

