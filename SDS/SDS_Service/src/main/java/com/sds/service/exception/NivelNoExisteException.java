package com.sds.service.exception;

public class NivelNoExisteException extends Exception {

	private static final long serialVersionUID = -7572458254677693962L;

	public NivelNoExisteException(final String code, final String msg) {
		super(msg);
	}

}
