package com.sds.service.exception;

public class ObjetivoYaExisteException extends Exception {

	private static final long serialVersionUID = -951141259221678963L;

	public ObjetivoYaExisteException(final String code, final String msg) {
		super(msg);
	}
}
