package com.sds.exception;

public class DataBaseException extends RuntimeException {

	private static final long serialVersionUID = 0x59f740caf5771ccfL;

	public DataBaseException(final Throwable rootCause) {
		super(rootCause);
	}

}
