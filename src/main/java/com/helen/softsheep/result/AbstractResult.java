package com.helen.softsheep.result;

import java.io.Serializable;

public abstract class AbstractResult implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 是否处理成功
	 */
	protected boolean success;

	/**
	 * 返回的code码
	 */
	protected String code;

	/**
	 * 返回的提示信息
	 */
	protected String msg;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
