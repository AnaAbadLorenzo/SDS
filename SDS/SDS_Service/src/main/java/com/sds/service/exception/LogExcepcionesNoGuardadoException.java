package com.sds.service.exception;

public class LogExcepcionesNoGuardadoException extends Exception {

	private static final long serialVersionUID = -7048346048650728946L;

	public LogExcepcionesNoGuardadoException(final String code, final String msg) {
		super(msg);
	}

}
