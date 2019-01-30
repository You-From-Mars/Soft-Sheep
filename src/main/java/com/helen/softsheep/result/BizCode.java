package com.helen.softsheep.result;

public enum BizCode implements ResultCode {
	
	/**
	 * 其他业务码定义 5001+n
	 */
	CODE_COMMON_ERROR(5001, "common error");

	private int code;

	private String msg;

	BizCode(int code, String msg) {
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
