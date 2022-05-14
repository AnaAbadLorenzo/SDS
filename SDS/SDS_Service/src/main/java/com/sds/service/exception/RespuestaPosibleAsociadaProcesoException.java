package com.sds.service.exception;

public class RespuestaPosibleAsociadaProcesoException extends Exception {

	private static final long serialVersionUID = 3119270227586983780L;

	public RespuestaPosibleAsociadaProcesoException(final String code, final String msg) {
		super(msg);
	}
}
