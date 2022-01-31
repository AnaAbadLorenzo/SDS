package com.sds.service.exception;

public class EmpresaYaExisteException extends Exception {

	private static final long serialVersionUID = -5656551441164553757L;

	public EmpresaYaExisteException(final String code, final String msg) {
		super(msg);
	}

}
