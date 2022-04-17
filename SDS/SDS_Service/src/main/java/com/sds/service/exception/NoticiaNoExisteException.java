package com.sds.service.exception;

public class NoticiaNoExisteException extends Exception {

	private static final long serialVersionUID = -6617579761567634816L;

	public NoticiaNoExisteException(final String code, final String msg) {
		super(msg);
	}
}
