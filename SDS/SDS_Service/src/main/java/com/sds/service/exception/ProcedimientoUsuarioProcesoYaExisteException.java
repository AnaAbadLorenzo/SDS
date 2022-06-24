package com.sds.service.exception;

public class ProcedimientoUsuarioProcesoYaExisteException extends Exception {

	private static final long serialVersionUID = -7866003189153135640L;

	public ProcedimientoUsuarioProcesoYaExisteException(final String code, final String msg) {
		super(msg);
	}
}
