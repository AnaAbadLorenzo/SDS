package com.sds.model;

public enum RespCode {

	LOGIN_VACIO("LOGIN_VACIO", "Los datos de usuario y contraseña no pueden ser vacíos."), //
	USUARIO_NO_ENCONTRADO("USUARIO_NO_EXISTE", "El usuario no existe."), //
	PASSWORD_INCORRECTO("PASSWORD_INCORRECTO", "La contraseña no coincide con el usuario."), //
	LOGIN_OK("LOGIN_OK", "Usuario logeado correctamente."), //
	TEST_ATRIBUTOS_LOGIN_OK("TEST_ATRIBUTOS_LOGIN_OK",
			"Los tests de atributos para el login se han ejecutado correctamente"), //
	TEST_ATRIBUTOS_LOGIN_KO("TEST_ATRIBUTOS_LOGIN_KO",
			"Se ha producido un error al ejecutar los tests de atributos para el login"), //
	TEST_ACCIONES_LOGIN_OK("TEST_ACCIONES_LOGIN_OK", "EL test de acciones para el login se ha ejecutado correctamente"), //
	TEST_ACCIONES_LOGIN_KO("TEST_ACCIONES_LOGIN_KO",
			"Se ha producido un error al ejecutar los tests de acciones para el login");

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
