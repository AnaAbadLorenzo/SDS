package com.sds.service.exception;

public class RolNoExisteException extends Exception {

	private static final long serialVersionUID = 1586452466480324333L;

	public RolNoExisteException(final String code, final String msg) {
		super(msg);
	}
}
