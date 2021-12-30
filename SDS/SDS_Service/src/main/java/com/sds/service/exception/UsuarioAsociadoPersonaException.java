package com.sds.service.exception;

public class UsuarioAsociadoPersonaException extends Exception {

	private static final long serialVersionUID = 7243496657276316041L;

	public UsuarioAsociadoPersonaException(final String code, final String msg) {
		super(msg);
	}
}
