package com.sds.service.exception;

public class MailNoEnviadoException extends Exception {

	private static final long serialVersionUID = 8821920310436907890L;

	public MailNoEnviadoException(final String code, final String msg) {
		super(msg);
	}
}
