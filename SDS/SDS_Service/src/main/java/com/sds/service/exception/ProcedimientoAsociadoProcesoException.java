package com.sds.service.exception;

public class ProcedimientoAsociadoProcesoException extends Exception {

	private static final long serialVersionUID = -8761497745665135832L;

	public ProcedimientoAsociadoProcesoException(final String code, final String msg) {
		super(msg);
	}
}
