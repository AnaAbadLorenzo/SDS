package com.sds.service.exception;

public class PlanYaExisteException extends Exception {

	private static final long serialVersionUID = -1611735459850286393L;

	public PlanYaExisteException(final String code, final String msg) {
		super(msg);
	}
}
