package com.helen.softsheep.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.helen.softsheep.dao.FollowDao;
import com.helen.softsheep.dao.UserDao;
import com.helen.softsheep.entity.FollowEntity;
import com.helen.softsheep.entity.UserEntity;
import com.helen.softsheep.result.CommonCode;
import com.helen.softsheep.result.GenericResult;

@RestController
public class FollowController {
	@Autowired
	private UserDao userDao;
	@Autowired
	private FollowDao followDao;
	@RequestMapping(value = "/softsheep/follow")
	@ResponseBody
	public GenericResult<String> follow(HttpServletRequest req, @RequestBody Map<String, Object> params) throws Exception {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("userUuid");
		if (id == null) {
			return GenericResult.fail(CommonCode.CODE_NO_LOGIN);
		}
		UserEntity user = userDao.findUserById(id);
		String followId = (String) params.get("id");
		String followingName = (String) params.get("followingName");
		FollowEntity follow = new FollowEntity();
		follow.setFollowId(followId);
		follow.setId(id);
		follow.setFollowingName(followingName);
		followDao.saveFollow(follow);
		return GenericResult.success("success");
	}
}
