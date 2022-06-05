package com.sds.service.exception;

public class ProcesoAsociadoProcedimientoException extends Exception {

	private static final long serialVersionUID = 5858056815674938068L;

	public ProcesoAsociadoProcedimientoException(final String code, final String msg) {
		super(msg);
	}
}
