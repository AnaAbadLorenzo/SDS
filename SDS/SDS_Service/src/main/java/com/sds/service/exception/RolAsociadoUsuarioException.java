package com.sds.service.exception;

public class RolAsociadoUsuarioException extends Exception{

	private static final long serialVersionUID = 2255764509374765448L;

	public RolAsociadoUsuarioException(final String code, final String msg) {
		super(msg);
	}
}
