package com.sds.service.exception;

public class LogAccionesNoGuardadoException extends Exception {

	private static final long serialVersionUID = -7048346048650728946L;

	public LogAccionesNoGuardadoException(final String code, final String msg) {
		super(msg);
	}

}
