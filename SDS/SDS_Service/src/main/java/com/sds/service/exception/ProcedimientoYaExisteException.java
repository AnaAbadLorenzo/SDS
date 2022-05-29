package com.sds.service.exception;

public class ProcedimientoYaExisteException extends Exception {

	private static final long serialVersionUID = 5299648211083401824L;

	public ProcedimientoYaExisteException(final String code, final String msg) {
		super(msg);
	}

}
