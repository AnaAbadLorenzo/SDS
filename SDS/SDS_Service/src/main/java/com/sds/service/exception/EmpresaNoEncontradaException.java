package com.sds.service.exception;

public class EmpresaNoEncontradaException extends Exception {

	private static final long serialVersionUID = 298921413617528404L;

	public EmpresaNoEncontradaException(final String code, final String msg) {
		super(msg);
	}
}
