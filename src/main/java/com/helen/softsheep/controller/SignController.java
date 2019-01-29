package com.helen.softsheep.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.helen.softsheep.dao.UserDao;
import com.helen.softsheep.entity.UserEntity;
import com.helen.softsheep.response.UserBody;
import com.helen.softsheep.result.CommonCode;
import com.helen.softsheep.result.GenericResult;

@RestController
public class SignController {

	public Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserDao UserDao;

	@RequestMapping(value = "/softsheep/signin")
	@ResponseBody
	public GenericResult<UserBody> signIn(HttpServletRequest req, @RequestBody Map<String, Object> params)
			throws Exception {
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
				logger.info("session创建成功，session的id是：" + sessionId);
			} else {
				logger.info("服务器已经存在该session了，session的id是：" + sessionId);
			}
			UserBody User = new UserBody();
			User.email = _email;
			User.userName = UserDao.findUserByEmail(_email).getUsername();
			return GenericResult.success(User);
		} else {
			return GenericResult.fail(CommonCode.CODE_NO_LOGIN);
		}
	}

	@RequestMapping(value = "/softsheep/loginout")
	@ResponseBody
	public GenericResult<String> loginOut(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		Cookie[] cookies = req.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName() == "JSESSIONID") {
				cookie.setValue(null);
				cookie.setMaxAge(0);
				res.addCookie(cookie);
				break;
			}
		}
		session.invalidate();
		return GenericResult.success("退出成功");
	}

	@RequestMapping(value = "/softsheep/signup")
	@ResponseBody
	public GenericResult<String> signUp(HttpServletRequest req, @RequestBody Map<String, Object> params)
			throws Exception {
		String _email = (String) params.get("email");
		UserEntity user = UserDao.findUserByEmail(_email);
		if (user == null) {
			String _username = (String) params.get("username");
			String _password = (String) params.get("password");
			String _sex = (String) params.get("sex");
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			UserEntity _user = new UserEntity();
			_user.setUsername(_username);
			_user.setPassword(_password);
			_user.setEmail(_email);
			_user.setSex(_sex);
			_user.setUserUuid(uuid);
			UserDao.saveUser(_user);
			return GenericResult.success("注册成功");
		} else {
			return GenericResult.success("该账户已经存在");
		}
	}
}
