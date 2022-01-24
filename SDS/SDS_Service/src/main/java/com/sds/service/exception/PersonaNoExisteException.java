package com.sds.service.exception;

public class PersonaNoExisteException extends Exception {

	private static final long serialVersionUID = 4795498769071199267L;

	public PersonaNoExisteException(final String code, final String msg) {
		super(msg);
	}
}
