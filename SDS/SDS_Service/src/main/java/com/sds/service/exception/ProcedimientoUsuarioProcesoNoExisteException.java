package com.sds.service.exception;

public class ProcedimientoUsuarioProcesoNoExisteException extends Exception {

	private static final long serialVersionUID = -7866003189153135640L;

	public ProcedimientoUsuarioProcesoNoExisteException(final String code, final String msg) {
		super(msg);
	}

}
