package com.helen.softsheep.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Signout {
	@RequestMapping(value = "/softsheep/loginout")
	@ResponseBody
	public String index(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		Cookie[] cookies = req.getCookies();
		for (Cookie cookie: cookies) {
			if (cookie.getName() == "JSESSIONID") {
				cookie.setValue(null);
				cookie.setMaxAge(0);
				res.addCookie(cookie);
				break;
			}
		}
		session.invalidate();
		return "退出成功";
	}
}
