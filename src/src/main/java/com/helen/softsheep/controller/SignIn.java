package com.helen.softsheep.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.helen.softsheep.dao.UserDao;
import com.helen.softsheep.entity.UserEntity;
import com.helen.softsheep.response.UserBody;

@RestController
public class SignIn {
	@Autowired
	private UserDao UserDao;
	@RequestMapping(value = "/softsheep/signin")
	@ResponseBody
	public UserBody index(HttpServletRequest req, @RequestBody Map<String, Object> params) throws Exception {
		String _email = (String) params.get("email");
		String _password = (String) params.get("password");
		UserEntity user = UserDao.findUserByEmail(_email);
		if (user != null && _password.equals(user.getPassword())) {
			HttpSession session = req.getSession();
			session.setAttribute("email", _email);
			session.setAttribute("userUuid", user.getUserUuid());
			session.setAttribute("userName", user.getUsername());
			String sessionId = session.getId();
			if (session.isNew()) {
				System.out.println("session创建成功，session的id是：" + sessionId);
			}else {
				System.out.println("服务器已经存在该session了，session的id是："+ sessionId);
			}
			UserBody User = new UserBody();
			User.email = _email;
			User.userName = UserDao.findUserByEmail(_email).getUsername();
			return User;
		} else {
			throw new RuntimeException("User or password is not correct.");
		}
	}
}


