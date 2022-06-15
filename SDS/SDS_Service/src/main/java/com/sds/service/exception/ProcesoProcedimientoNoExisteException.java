package com.sds.service.exception;

public class ProcesoProcedimientoNoExisteException extends Exception {

	private static final long serialVersionUID = 4732549641816030767L;

	public ProcesoProcedimientoNoExisteException(final String code, final String msg) {
		super(msg);
	}
}
