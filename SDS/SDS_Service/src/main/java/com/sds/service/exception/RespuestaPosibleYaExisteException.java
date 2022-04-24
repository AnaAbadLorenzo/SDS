package com.sds.service.exception;

public class RespuestaPosibleYaExisteException extends Exception {

	private static final long serialVersionUID = 769917140243707987L;

	public RespuestaPosibleYaExisteException(final String code, final String msg) {
		super(msg);
	}

}
