package com.sds.service.exception;

public class FuncionalidadNoExisteException extends Exception {

	private static final long serialVersionUID = 319583043447015825L;

	public FuncionalidadNoExisteException(final String code, final String msg) {
		super(msg);
	}
}
