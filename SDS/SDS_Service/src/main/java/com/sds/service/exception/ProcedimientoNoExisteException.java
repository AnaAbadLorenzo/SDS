package com.sds.service.exception;

public class ProcedimientoNoExisteException extends Exception {

	private static final long serialVersionUID = 7844981552938052736L;

	public ProcedimientoNoExisteException(final String code, final String msg) {
		super(msg);
	}
}
