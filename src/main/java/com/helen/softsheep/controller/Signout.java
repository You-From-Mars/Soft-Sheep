package com.helen.softsheep.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.helen.softsheep.result.GenericResult;

@RestController
public class Signout {
	@RequestMapping(value = "/softsheep/loginout")
	@ResponseBody
	public GenericResult<String> index(HttpServletRequest req, HttpServletResponse res) throws Exception {
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
		return GenericResult.success("退出成功");
	}
}
