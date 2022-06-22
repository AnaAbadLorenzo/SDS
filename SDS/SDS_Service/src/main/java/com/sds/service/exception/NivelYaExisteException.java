package com.sds.service.exception;

public class NivelYaExisteException extends Exception {

	private static final long serialVersionUID = -785062591628547207L;

	public NivelYaExisteException(final String code, final String msg) {
		super(msg);
	}
}
