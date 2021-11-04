package com.sds.service.exception;

public class UsuarioNoEncontrado extends Exception {

	private static final long serialVersionUID = -7048346048650728946L;

	public UsuarioNoEncontrado(final String code, final String msg) {
		super(msg);
	}

}
