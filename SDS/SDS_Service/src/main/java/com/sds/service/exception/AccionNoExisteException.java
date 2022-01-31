package com.sds.service.exception;

public class AccionNoExisteException extends Exception {

	private static final long serialVersionUID = 6814986049089151126L;

	public AccionNoExisteException(final String code, final String msg) {
		super(msg);
	}
}
