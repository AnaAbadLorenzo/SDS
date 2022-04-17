package com.sds.service.exception;

public class NoticiaYaExisteException extends Exception {

	private static final long serialVersionUID = 5236791959106057663L;

	public NoticiaYaExisteException(final String code, final String msg) {
		super(msg);
	}
}
