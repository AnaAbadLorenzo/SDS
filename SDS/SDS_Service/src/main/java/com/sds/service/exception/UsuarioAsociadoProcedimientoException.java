package com.sds.service.exception;

public class UsuarioAsociadoProcedimientoException extends Exception {

	private static final long serialVersionUID = -4349435931952138614L;

	public UsuarioAsociadoProcedimientoException(final String code, final String msg) {
		super(msg);
	}
}
