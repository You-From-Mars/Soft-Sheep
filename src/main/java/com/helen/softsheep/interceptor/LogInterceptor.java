package com.helen.softsheep.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class LogInterceptor {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Pointcut("execution (* com.helen.softsheep.controller..*.*(..))")
	public void log() {
	}

	@Around("log()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = null;
		long start = System.currentTimeMillis();

		try {
			result = joinPoint.proceed();
		} catch (Throwable t) {
			long end = System.currentTimeMillis();
			logger.error("Request[method:{},duration:{}ms]", joinPoint.getSignature(), end - start);
			throw t;
		}

		long end = System.currentTimeMillis();
		logger.info("Request[method:{},duration:{}ms]", joinPoint.getSignature(), end - start);
		return result;
	}
}
