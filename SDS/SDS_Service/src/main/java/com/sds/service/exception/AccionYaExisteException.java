package com.sds.service.exception;

public class AccionYaExisteException extends Exception {

	private static final long serialVersionUID = -3238745055089182537L;

	public AccionYaExisteException(final String code, final String msg) {
		super(msg);
	}
}
