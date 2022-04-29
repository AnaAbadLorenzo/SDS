package com.sds.service.exception;

public class PlanNoExisteException extends Exception {

	private static final long serialVersionUID = 1L;

	public PlanNoExisteException(final String code, final String msg) {
		super(msg);
	}
}
