package com.sds.service.exception;

public class ProcedimientoUsuarioNoExisteException extends Exception {

	private static final long serialVersionUID = 6753713663889208890L;

	public ProcedimientoUsuarioNoExisteException(final String code, final String msg) {
		super(msg);
	}
}
