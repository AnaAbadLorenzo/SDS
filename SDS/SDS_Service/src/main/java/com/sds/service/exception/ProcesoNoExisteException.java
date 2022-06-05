package com.sds.service.exception;

public class ProcesoNoExisteException extends Exception{

	private static final long serialVersionUID = -3126813366867282249L;

	public ProcesoNoExisteException(final String code, final String msg) {
		super(msg);
	}
}
