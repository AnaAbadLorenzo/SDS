package com.sds.service.exception;

public class ProcesoRespuestaPosibleYaExisteException extends Exception {

	private static final long serialVersionUID = -7911579597711540588L;

	public ProcesoRespuestaPosibleYaExisteException(final String code, final String msg) {
		super(msg);
	}
}
