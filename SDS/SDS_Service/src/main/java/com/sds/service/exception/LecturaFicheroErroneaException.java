package com.sds.service.exception;

public class LecturaFicheroErroneaException extends Exception {

	private static final long serialVersionUID = 1313891503804437462L;

	public LecturaFicheroErroneaException(final String code, final String msg) {
		super(msg);
	}
}
