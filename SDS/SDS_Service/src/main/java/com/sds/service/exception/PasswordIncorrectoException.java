package com.sds.service.exception;

public class PasswordIncorrectoException extends Exception {

	private static final long serialVersionUID = -7048346048650728946L;

	public PasswordIncorrectoException(final String code, final String msg) {
		super(msg);
	}

}
