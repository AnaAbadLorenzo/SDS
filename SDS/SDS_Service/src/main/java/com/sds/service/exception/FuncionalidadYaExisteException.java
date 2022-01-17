package com.sds.service.exception;

public class FuncionalidadYaExisteException extends Exception {

	private static final long serialVersionUID = 5345315309255193983L;

	public FuncionalidadYaExisteException(final String code, final String msg) {
		super(msg);
	}

}
