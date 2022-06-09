package com.sds.service.exception;

public class ProcedimientoUsuarioYaExisteException extends Exception {

	private static final long serialVersionUID = -4377479418471186172L;

	public ProcedimientoUsuarioYaExisteException(final String code, final String msg) {
		super(msg);
	}
}
