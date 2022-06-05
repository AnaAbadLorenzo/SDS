package com.sds.service.exception;

public class ProcesoYaExisteException extends Exception {

	private static final long serialVersionUID = -7405286332370638860L;

	public ProcesoYaExisteException(final String code, final String msg) {
		super(msg);
	}

}
