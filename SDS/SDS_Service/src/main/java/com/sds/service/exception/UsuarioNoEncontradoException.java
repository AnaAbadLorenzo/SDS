package com.sds.service.exception;

public class UsuarioNoEncontradoException extends Exception {

	private static final long serialVersionUID = -7048346048650728946L;

	public UsuarioNoEncontradoException(final String code, final String msg) {
		super(msg);
	}

}
