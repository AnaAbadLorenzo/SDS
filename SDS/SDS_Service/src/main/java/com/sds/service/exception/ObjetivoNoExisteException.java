package com.sds.service.exception;

public class ObjetivoNoExisteException extends Exception {

	private static final long serialVersionUID = -7461452829417774137L;

	public ObjetivoNoExisteException(final String code, final String msg) {
		super(msg);
	}
}
