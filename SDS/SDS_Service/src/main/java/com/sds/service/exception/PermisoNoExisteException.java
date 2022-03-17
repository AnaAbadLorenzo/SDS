package com.sds.service.exception;

public class PermisoNoExisteException extends Exception {

	private static final long serialVersionUID = 7682833000566757115L;

	public PermisoNoExisteException(final String code, final String msg) {
		super(msg);
	}
}