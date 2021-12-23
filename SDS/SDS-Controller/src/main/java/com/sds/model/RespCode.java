package com.sds.model;

public enum RespCode {

	LOGIN_VACIO("LOGIN_VACIO", "Los datos de usuario y contraseña no pueden ser vacíos."), //
	USUARIO_NO_ENCONTRADO("USUARIO_NO_EXISTE", "El usuario no existe."), //
	PASSWORD_INCORRECTO("PASSWORD_INCORRECTO", "La contraseña no coincide con el usuario."), //
	LOGIN_OK("LOGIN_OK", "Usuario logeado correctamente."), //
	ROL_VACIO("ROL_VACIO", "El nombre del rol y/o la descripcion no pueden ser vacios"), //
	ROL_MODIFICADO("ROL_MODIFICADO", "El rol se ha modificado correctamente"), //
	ROL_ELIMINADO("ROL_ELIMINADO", "El rol se ha eliminado correctamente"), //
	ROL_GUARDADO("ROL_GUARDADO", "El rol se ha guadado correctamente"), //
	ROLES_LISTADOS("ROLES_LISTADOS", "Los roles se han listado correctamente"), //
	ROLES_ELIMINADOS_LISTADOS("ROLES_ELIMINADOS_LISTADOS", "Los roles eliminados se han listado correctamente"), //
	REGISTRO_VACIO("REGISTRO_VACIO", "Los datos de persona y usuario no pueden ser vacíos."), //
	ROL_ENCONTRADO("ROL_ENCONTRADO", "El rol se ha encontrado con éxito"), //
	USUARIO_YA_EXISTE("USUARIO_YA_EXISTE", "El usuario ya existe"), //
	PERSONA_YA_EXISTE("PERSONA_YA_EXISTE", "La persona ya existe"), //
	EMPRESA_YA_EXISTE("EMPRESA_YA_EXISTE", "La empresa ya existe"), //
	ROL_YA_EXISTE_EXCEPTION("ROL_YA_EXISTE", "El rol ya existe"), //
	ROL_NO_EXISTE_EXCEPTION("ROL_NO_EXISTE", "El rol no existe"), //
	NO_HAY_ROLES_EXCEPTION("NO_HAY_ROLES", "No existe ningún rol registrado"), //
	ROL_ASOCIADO_USUARIO_EXCEPTION("ROL_ASOCIADO_USUARIO", "Hay un usuario que tiene asociado el rol a eliminar"), //
	REGISTRO_OK("REGISTRO_OK", "Registro realizado correctamente"), //
	TEST_ATRIBUTOS_LOGIN_OK("TEST_ATRIBUTOS_LOGIN_OK",
			"Los tests de atributos para el login se han ejecutado correctamente"), //
	TEST_ATRIBUTOS_LOGIN_KO("TEST_ATRIBUTOS_LOGIN_KO",
			"Se ha producido un error al ejecutar los tests de atributos para el login"), //
	TEST_ACCIONES_LOGIN_OK("TEST_ACCIONES_LOGIN_OK", "EL test de acciones para el login se ha ejecutado correctamente"), //
	TEST_ACCIONES_LOGIN_KO("TEST_ACCIONES_LOGIN_KO",
			"Se ha producido un error al ejecutar los tests de acciones para el login"), //
	TEST_ATRIBUTOS_REGISTRO_OK("TEST_ATRIBUTOS_REGISTRO_OK",
			"Los tests de atributos para el registro se han ejecutado correctamente"), //
	TEST_ATRIBUTOS_REGISTRO_KO("TEST_ATRIBUTOS_REGISTRO_KO",
			"Se ha producido un error al ejecutar los tests de atributos para el registro"), //
	TEST_ACCIONES_REGISTRO_OK("TEST_ACCIONES_REGISTRO_OK",
			"EL test de acciones para el registro se ha ejecutado correctamente"), //
	TEST_ACCIONES_REGISTRO_KO("TEST_ACCIONES_REGISTRO_KO",
			"Se ha producido un error al ejecutar los tests de acciones para el registro"), //
	TEST_ATRIBUTOS_ROL_KO("TEST_ATRIBUTOS_ROL_KO",
			"Se ha producido un error al ejecutar los test de atributo para el rol"), //
	TEST_ATRIBUTOS_ROL_OK("TEST_ATRIBUTO_ROL_OK", "Los tests de atributos para el rol se han ejecutado correctamente"), //
	TEST_ACCIONES_ROL_KO("TEST_ACCIONES_ROL_KO", "Se ha producido un fallo alejecutar los tests de acciones para rol"), //
	TEST_ACCIONES_ROL_OK("TEST_ACCIONES_ROL_OK", "Los test de acciones se han ejecutado correctamente");

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