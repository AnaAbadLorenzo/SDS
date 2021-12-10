package com.sds.service.exception;

public class NoHayRolesException extends Exception {

	private static final long serialVersionUID = 6985983038105838641L;

	public NoHayRolesException(final String code, final String msg) {
		super(msg);
	}

}
