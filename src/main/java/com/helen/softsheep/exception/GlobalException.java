package com.helen.softsheep.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.helen.softsheep.result.CommonCode;
import com.helen.softsheep.result.GenericResult;

@ControllerAdvice
public class GlobalException {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public GenericResult<String> handle(Exception e) {
		logger.error("Internal server error=>", e);
		return GenericResult.fail(CommonCode.CODE_SERVER_ERROR);
	}
}
