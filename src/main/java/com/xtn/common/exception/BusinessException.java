
package com.xtn.common.exception;

/**
 * 自定义异常
 *
 * @author xCoder
 */
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String msg;
    private String code = "500";
    
    public BusinessException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public BusinessException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public BusinessException(String code, String msg) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}
	
	public BusinessException(String code, String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
