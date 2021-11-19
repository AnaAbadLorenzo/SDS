package com.sds.service.exception;

public class UsuarioYaExisteException extends Exception {

	private static final long serialVersionUID = 2481948897169188911L;

	public UsuarioYaExisteException(final String code, final String msg) {
		super(msg);
	}
}
