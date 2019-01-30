package com.helen.softsheep.result;

public enum CommonCode implements ResultCode {
	/**
	 * 成功返回码
	 */
	CODE_SUCCESS(200, "success"),

	/**
	 * 用户未登录
	 */
	CODE_NO_LOGIN(401, "用户未登录"),

	/**
	 * 参数缺失
	 */
	CODE_PARAM_REQUIRED(402, "参数缺失"),

	/**
	 * 无权限访问
	 */
	CODE_NO_AUTH(403, "无权限访问"),

	/**
	 * 请求方法错误
	 */
	CODE_NOT_EXIST(404, "请求方法错误"),

	/**
	 * 请求超时
	 */
	CODE_REQUEST_TIMEOUT(408, "请求超时"),

	/**
	 * 请求参数错误
	 */
	CODE_PARAM_ERROR(4001, "请求参数错误"),

	/**
	 * 服务器错误
	 */
	CODE_SERVER_ERROR(500, "服务器错误"),

	/**
	 * 其他业务码定义
	 */
	CODE_COMMON_ERROR(5001, "common error");

	private int code;

	private String msg;

	CommonCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public int code() {
		return code;
	}

	@Override
	public String msg() {
		return msg;
	}

}
