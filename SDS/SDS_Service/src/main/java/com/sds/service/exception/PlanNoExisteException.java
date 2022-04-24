package com.sds.service.exception;

public class PlanNoExisteException extends Exception {

	public PlanNoExisteException(final String code, final String msg) {
		super(msg);
	}
}
