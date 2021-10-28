package com.sds.model;

public class RespEntity {

	private final String code;
	private final String msg;
	private Object data;

	public RespEntity(final RespCode respCode) {
		this.code = respCode.getCode();
		this.msg = respCode.getMsg();
	}

	public RespEntity(final RespCode respCode, final Object data) {
		this(respCode);
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(final Object data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
