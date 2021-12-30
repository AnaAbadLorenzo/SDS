package com.sds.service.exception;

public class EmpresaAsociadaPersonasException extends Exception {

	private static final long serialVersionUID = -6862960706172442556L;

	public EmpresaAsociadaPersonasException(final String code, final String msg) {
		super(msg);
	}
}
