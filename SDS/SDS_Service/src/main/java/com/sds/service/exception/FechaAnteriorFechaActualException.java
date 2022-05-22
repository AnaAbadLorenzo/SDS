package com.sds.service.exception;

public class FechaAnteriorFechaActualException extends Exception {

	private static final long serialVersionUID = -6772948389304981233L;

	public FechaAnteriorFechaActualException(final String code, final String msg) {
		super(msg);
	}
}
