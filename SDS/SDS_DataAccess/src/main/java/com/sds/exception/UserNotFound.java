package com.sds.exception;

public class UserNotFound extends Exception {

	private static final long serialVersionUID = -7048346048650728946L;

	public UserNotFound(final String msg) {
		super(msg);
	}

}
