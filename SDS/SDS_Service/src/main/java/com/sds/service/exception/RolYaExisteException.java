package com.sds.service.exception;

public class RolYaExisteException extends Exception {

	private static final long serialVersionUID = 5278607876431848181L;

	public RolYaExisteException(final String code, final String msg) {
		super(msg);
	}
}
