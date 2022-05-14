package com.sds.service.exception;

public class RespuestaPosibleNoExisteException extends Exception {

	private static final long serialVersionUID = 2159246022512990078L;

	public RespuestaPosibleNoExisteException(final String code, final String msg) {
		super(msg);
	}
}
