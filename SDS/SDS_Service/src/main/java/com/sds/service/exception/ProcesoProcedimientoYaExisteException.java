package com.sds.service.exception;

public class ProcesoProcedimientoYaExisteException extends Exception {

	private static final long serialVersionUID = 5655462516677304949L;

	public ProcesoProcedimientoYaExisteException(final String code, final String msg) {
		super(msg);
	}
}
