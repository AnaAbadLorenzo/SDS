package com.sds.service.exception;

public class EmailNoEncontradoException extends Exception {

	private static final long serialVersionUID = 505760775371666906L;

	public EmailNoEncontradoException(final String code, final String msg) {
		super(msg);
	}
}
