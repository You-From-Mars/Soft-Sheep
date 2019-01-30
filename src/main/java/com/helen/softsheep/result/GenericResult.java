package com.helen.softsheep.result;

/**
 * 公共数据返回对象
 * 
 */
public class GenericResult<T> extends AbstractResult {

	private static final long serialVersionUID = 1L;
	
	private T data;

	public GenericResult() {
		super();
	}

	public GenericResult(T data) {
		this.data = data;
		this.success = true;
		this.code = String.valueOf(CommonCode.CODE_SUCCESS.code());
		this.msg = CommonCode.CODE_SUCCESS.msg();
	}

	/**
	 * 失败返回对象
	 * 
	 * @param code 框架CODE码
	 * @param msg
	 * @return
	 */
	public static <T> GenericResult<T> fail(ResultCode code, String... msg) {
		GenericResult<T> genericResult = new GenericResult<>();
		genericResult.setSuccess(false);
		genericResult.setCode(String.valueOf(code.code()));
		genericResult.setMsg(msg.length == 0 ? code.msg() : msg[0]);
		return genericResult;
	}

	/**
	 * 失败返回对象
	 * 
	 * @param code 返回CODE码
	 * @param msg
	 * @return
	 */
	public static <T> GenericResult<T> fail(String code, String... msg) {
		GenericResult<T> genericResult = new GenericResult<>();
		genericResult.setSuccess(false);
		genericResult.setCode(code);
		genericResult.setMsg(msg.length == 0 ? null : msg[0]);
		return genericResult;
	}

	/**
	 * 成功返回对象, 无数据携带
	 * 
	 * @return
	 */
	public static <T> GenericResult<T> success() {
		return success(null);
	}

	/**
	 * 成功返回对象
	 * 
	 * @param data 数据对象
	 * @return
	 */
	public static <T> GenericResult<T> success(T data) {
		GenericResult<T> genericResult = new GenericResult<>();
		genericResult.setSuccess(true);
		genericResult.setCode(String.valueOf(CommonCode.CODE_SUCCESS.code()));
		genericResult.setMsg(CommonCode.CODE_SUCCESS.msg());
		genericResult.setData(data);
		return genericResult;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("GenericResult{");
		sb.append("data=").append(data);
		sb.append(", success=").append(success);
		sb.append(", code='").append(code).append('\'');
		sb.append(", msg='").append(msg).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
