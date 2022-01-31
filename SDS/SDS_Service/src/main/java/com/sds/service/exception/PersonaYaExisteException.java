package com.sds.service.exception;

public class PersonaYaExisteException extends Exception {

	private static final long serialVersionUID = -2299778405836989382L;

	public PersonaYaExisteException(final String code, final String msg) {
		super(msg);
	}
}
