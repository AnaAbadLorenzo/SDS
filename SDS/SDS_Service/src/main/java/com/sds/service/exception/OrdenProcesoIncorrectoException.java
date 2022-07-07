package com.sds.service.exception;

public class OrdenProcesoIncorrectoException extends Exception {

	private static final long serialVersionUID = -951141259221678963L;

	public OrdenProcesoIncorrectoException(final String code, final String msg) {
		super(msg);
	}
}
