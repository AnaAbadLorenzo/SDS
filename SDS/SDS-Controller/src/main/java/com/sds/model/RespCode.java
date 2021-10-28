package com.sds.model;

public enum RespCode {

	LOGIN_BLANK("00001", "Los datos de usuario y contraseña no pueden ser vacíos."), //
	USER_NOT_FOUND("00002", "El usuario no existe."), //
	PASSWORD_INCORRECTO("00003", "La contraseña no coincide con el usuario."), //
	LOGIN_OK("10001", "Usuario logeado correctamente.");

	private String code;
	private String msg;

	RespCode(final String code, final String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
