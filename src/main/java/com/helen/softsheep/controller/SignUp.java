package com.helen.softsheep.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.helen.softsheep.dao.UserDao;
import com.helen.softsheep.entity.UserEntity;
import com.helen.softsheep.result.GenericResult;

@RestController
public class SignUp {
	@Autowired
	private UserDao UserDao;
	@RequestMapping(value = "/softsheep/signup")
	@ResponseBody
	public GenericResult<String> index(HttpServletRequest req, @RequestBody Map<String, Object> params) throws Exception {
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
		}  else {
			return GenericResult.success("该账户已经存在");
		}
	}

}
