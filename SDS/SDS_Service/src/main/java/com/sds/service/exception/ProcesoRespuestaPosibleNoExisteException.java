package com.sds.service.exception;

public class ProcesoRespuestaPosibleNoExisteException extends Exception {

	private static final long serialVersionUID = 2841071884167269358L;

	public ProcesoRespuestaPosibleNoExisteException(final String code, final String msg) {
		super(msg);
	}
}
