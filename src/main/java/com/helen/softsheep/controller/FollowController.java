package com.helen.softsheep.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.helen.softsheep.dao.UserDao;
import com.helen.softsheep.entity.UserEntity;
import com.helen.softsheep.response.FollowType;
import com.helen.softsheep.result.CommonCode;
import com.helen.softsheep.result.GenericResult;

@RestController
public class FollowController {
	@Autowired
	private UserDao userDao;
	@RequestMapping(value = "/softsheep/follow")
	@ResponseBody
	public GenericResult<String> follow(HttpServletRequest req) throws Exception {
		HttpSession session = req.getSession();
		String userUuid = (String) session.getAttribute("userUuid");
		if (userUuid == null) {
			return GenericResult.fail(CommonCode.CODE_NO_LOGIN);
		}
		UserEntity user = userDao.findUserById(userUuid);
		String followUserId = req.getParameter("id");
		String followName = req.getParameter("name");
		FollowType following = new FollowType();
		following.setId(followUserId);
		following.setName(followName);
		user.setFollower(following);
		userDao.updateUser(user);
		return GenericResult.success("success");
	}
}
