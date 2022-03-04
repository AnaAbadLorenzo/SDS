package com.sds.model;

public enum RespCode {

	PARSE_EXCEPTION("PARSE_EXCEPTION", "Error al parsear los datos"), //

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
	PERSONA_NO_EXISTE("PERSONA_NO_EXISTE", "La persona no existe"), //
	PERSONAS_LISTADAS("PERSONAS_LISTADAS", "Las personas se han listado correctamente"), //
	PERSONA_ENCONTRADA("PERSONA_ENCONTRADA", "La persona se ha encontrado correctamente"), //
	PERSONA_ELIMINADA("PERSONA_ELIMINADA", "La persona se ha eliminado correctamente"), //
	PERSONA_VACIA("PERSONA_VACIA", "Los datos de persona no pueden ser vacíos"), //
	PERSONA_GUARDADA("PERSONA_GUARDADA", "La persona se ha guardado correctamente"), //
	PERSONA_MODIFICADA("PERSONA_MODIFICADA", "La persona se ha modificado correctamente"), //
	PERSONA_REACTIVADA("PERSONA_REACTIVADA", "La persona se ha reactivado correctamente"), //
	USUARIO_ASOCIADO_PERSONA_EXCEPTION("USUARIO_ASOCIADO_PERSONA_EXCEPTION", "La persona tiene un usuario asociado"), //
	ENVIO_EMAIL_EXCEPTION("ENVIO_EMAIL_EXCEPTION", "Ha ocurrido un errror al enviar el email"), //
	EMPRESA_YA_EXISTE("EMPRESA_YA_EXISTE", "La empresa ya existe"), //
	EMPRESA_ENCONTRADA("EMPRESA_ENCONTRADA", "La empresa se ha encontrado perfectamente"), //
	EMPRESAS_LISTADAS("EMPRESAS_LISTADAS", "Las empresas se han listado correctamente"), //
	EMPRESAS_LISTADAS_ELIMINADAS("EMPRESAS_LISTADAS_ELIMINADAS",
			"Las empresas eliminadas se han listado correctamente"), //
	EMPRESA_VACIA("EMPRESA_VACIA", "Los datos de la empresa son vacíos"), //
	EMPRESA_GUARDADA("EMPRESA_GUARDADA", "La empresa se ha guardado correctamente"), //
	EMPRESA_REACTIVADA("EMPRESA_REACTIVADA", "La empresa se ha reactivado correctamente"), //
	EMPRESA_YA_EXISTE_EXCEPTION("EMPRESA_YA_EXISTE_EXCEPTION", "La empresa ya existe"), //
	EMPRESA_MODIFICADA("EMPRESA_MODIFICADA", "La empresa se ha modificado correctamente"), //
	EMPRESA_NO_ENCONTRADA_EXCEPTION("EMPRESA_NO_ENCONTRADA_EXCEPTION", "La empresa no existe"), //
	EMPRESA_ELIMINADA("EMPRESA_ELIMINADA", "La empresa se ha eliminado correctamente"), //
	EMPRESA_ASOCIADA_PERSONAS_EXCEPTION("EMPRESA_ASOCIADA_PERSONAS_EXCEPTION", "La empresa tiene personas asociadas"), //
	EMAIL_NO_ENCONTRADO("EMAIL_NO_ENCONTRADO", "El email no existe"), //
	RECUPERAR_PASS_VACIO("RECUPERAR_PASS_VACIO", "El email y/o el usuario para recuperar la contraseña están vacíos"), //
	EMAIL_VACIO("EMAIL_VACIO", "El email de usuario es vacío"), //
	USUARIO_VACIO("USUARIO_VACIO", "El usuario es vacío"), //
	USUARIO_GUARDADO("USUARIO_GUARDADO", "El usuario se ha guardado correctamente"), //
	USUARIO_ELIMINADO("USUARIO_ELIMINADO", "El usuario se ha eliminado correctamente"), //
	USUARIO_MODIFICADO("USUARIO_MODIFICADO", "El usuario se ha modificado correctamente"), //
	USUARIO_REACTIVADO("USUARIO_REACTIVADO", "El usuario se ha reactivado correctamente"), //
	USUARIO_MODIFICAR_VACIO("USUARIO_MODIFICAR_VACIO", "El usuario y/o el rol es vacio"), //
	RECUPERAR_PASS_OK("RECUPERAR_PASS_OK", "La nueva contraseña se ha enviado correctamente"), //
	USUARIO_AÑADIR_VACIO("USUARIO_AÑADIR_VACIO", "Los datos de usuario y/o persona son vacios"), //
	ROL_YA_EXISTE_EXCEPTION("ROL_YA_EXISTE", "El rol ya existe"), //
	ROL_NO_EXISTE_EXCEPTION("ROL_NO_EXISTE", "El rol no existe"), //
	ROL_ASOCIADO_ACCION_FUNCIONALIDAD_EXCEPTION("ROL_ASOCIADO_ACCION_FUNCIONALIDAD_EXCEPTION",
			"El rol tiene asociado una accion y una funcionalidad"), //
	NO_HAY_ROLES_EXCEPTION("NO_HAY_ROLES", "No existe ningún rol registrado"), //
	ROL_ASOCIADO_USUARIO_EXCEPTION("ROL_ASOCIADO_USUARIO", "Hay un usuario que tiene asociado el rol a eliminar"), //
	ACCION_VACIA("ACCION_VACIA", "El nombre y/o la descripción de la acción están vacíos"), //
	ACCION_ENCONTRADA("ACCION_ECONTRADA", "La acción que busca se ha encontrado correctamente"), //
	ACCIONES_LISTADAS("ACCIONES_LISTADAS", "Las acciones se han listdo correctamente"), //
	ACCIONES_ELIMINADAS_LISTADAS("ACCIONES_ELIMINADAS_LISTADAS",
			"Las acciones eliminadas se han listado correctamente"), //
	ACCION_GUARDADA("ACCION_GUARDADA", "La acción se ha guadado correctamente"), //
	ACCION_MODIFICADA("ACCION_MODIFICADA", "La acción se ha modificado correctamente"), //
	ACCION_ELIMINADA("ACCION_ELIMINADA", "La acción se ha eliminado correctamente"), //
	ACCION_ASOCIADA_ROL_FUNCIONALIDAD_EXCEPTION("ACCION_ASOCIDADA_ROL_FUNCIONALIDAD",
			"La accion se encuentra asociada a una accion y funcionalidad"), //
	ACCION_YA_EXISTE_EXCEPTION("ACCION_YA_EXISTE", "La acción ya existe"), //
	ACCION_NO_EXISTE_EXCEPTION("ACCION_NO_EXISTE_EXCEPTION", "La accion que busca no existe"), //
	ACCION_REACTIVADA("ACCION_REACTIVADA", "La accion se ha reactivado correctamente"), //
	FUNCIONALIDAD_VACIA("FUNCIONALIDAD_VACIA", "La funcionalidad está vacía"), //
	FUNCIONALIDAD_ENCONTRADA("FUNCIONALIDAD_ENCONTRADA", "La funcionalidad se ha encontrado correctamente"), //
	FUNCIONALIDADES_LISTADAS("FUNCIONALIDADES_LISTADAS", "Las funcionalidades se han listado correctamente"), //
	FUNCIONALIDADES_ELIMINADAS_LISTADAS("FUNCIONALIDADES_ELIMINADAS_LISTADAS",
			"Las funcionalidades eliminadas se han listado correctamente"), //
	FUNCIONALIDAD_MODIFICADA("FUNCIONALIDAD_MODIFICADA", "La funcionalidad se ha modificado correctamente"), //
	FUNCIONALIDAD_ELIMINADA("FUNCIONALIDAD_ELIMINADA", "La funcionalidad se ha eliminado correctamente"), //
	FUNCIONALIDAD_ASOCIADA_ROL_ACCION_EXCEPTION("FUNCIONALIDAD_ASOCIADA_ROL_ACCION_EXCEPTION",
			"La funcionalidad está asociada a un rol y una funcionalidad"), //
	FUNCIONALIDAD_NO_EXISTE_EXCEPTION("FUNCIONALIDAD_NO_EXISTE_EXCEPTION", "La funcionalidad que busca no existe"), //
	FUNCIONALIDAD_YA_EXISTE_EXCEPTION("FUNCIONALIDAD_YA_EXISTE_EXCEPTION", "La funcionalidad que busca ya existe"), //
	FUNCIONALIDAD_GUARDADA("FUNCIONALIDAD_GUARDADA", "La funcionalidad se ha guardado correctamente"), //
	FUNCIONALIDAD_REACTIVADA("FUNCIONALIDAD_REACTIVADA", "La funcionalidad se ha reactivado correctamente"), //
	USUARIOS_LISTADOS("USUARIOS_LISTADOS", "Los usuarios se han listado correctamente"), //
	REGISTRO_OK("REGISTRO_OK", "Registro realizado correctamente"), //
	ROL_USUARIO_MODIFICADO_OK("ROL_USUARIO_MODIFICADO_OK", "El rol del usuario se ha modificado correctamente"), //
	LOG_ACCIONES_NO_GUARDADO("LOG_ACCIONES_NO_GUARDADO",
			"No se ha guardado el registro de acciones en la base de datos"), //
	LOG_EXCEPCIONES_NO_GUARDADO("LOG_EXCEPCIONES_NO_GUARDADO",
			"No se ha guardado el registro de excepciones en la base de datos"), //
	TEST_ATRIBUTOS_LOGIN_OK("TEST_ATRIBUTOS_LOGIN_OK",
			"Los tests de atributos para el login se han ejecutado correctamente"), //
	TEST_ATRIBUTOS_LOGIN_KO("TEST_ATRIBUTOS_LOGIN_KO",
			"Se ha producido un error al ejecutar los tests de atributos para el login"), //
	TEST_ACCIONES_LOGIN_OK("TEST_ACCIONES_LOGIN_OK", "El test de acciones para el login se ha ejecutado correctamente"), //
	TEST_ACCIONES_LOGIN_KO("TEST_ACCIONES_LOGIN_KO",
			"Se ha producido un error al ejecutar los tests de acciones para el login"), //
	TEST_ATRIBUTOS_REGISTRO_OK("TEST_ATRIBUTOS_REGISTRO_OK",
			"Los tests de atributos para el registro se han ejecutado correctamente"), //
	TEST_ATRIBUTOS_REGISTRO_KO("TEST_ATRIBUTOS_REGISTRO_KO",
			"Se ha producido un error al ejecutar los tests de atributos para el registro"), //
	TEST_ACCIONES_REGISTRO_OK("TEST_ACCIONES_REGISTRO_OK",
			"El test de acciones para el registro se ha ejecutado correctamente"), //
	TEST_ACCIONES_REGISTRO_KO("TEST_ACCIONES_REGISTRO_KO",
			"Se ha producido un error al ejecutar los tests de acciones para el registro"), //
	TEST_ATRIBUTOS_ROL_KO("TEST_ATRIBUTOS_ROL_KO",
			"Se ha producido un error al ejecutar los test de atributo para el rol"), //
	TEST_ATRIBUTOS_ROL_OK("TEST_ATRIBUTO_ROL_OK", "Los tests de atributos para el rol se han ejecutado correctamente"), //
	TEST_ACCIONES_ROL_KO("TEST_ACCIONES_ROL_KO", "Se ha producido un fallo alejecutar los tests de acciones para rol"), //
	TEST_ACCIONES_ROL_OK("TEST_ACCIONES_ROL_OK", "Los test de acciones se han ejecutado correctamente"), //
	TEST_ATRIBUTOS_ACCION_KO("TEST_ATRIBUTOS_ACCION_KO",
			"Se ha producido un error al ejecutar los test de atributos para accion"), //
	TEST_ATRIBUTOS_ACCION_OK("TEST_ATRIBUTOS_ACCION_OK",
			"Los test de atributo para acción se han ejecutado correctamente"), //
	TEST_ACCIONES_ACCION_KO("TEST_ACCIONES_ACCION_KO",
			"Se ha producido un fallo al ejecutar los test de acciones para accion"), //
	TEST_ACCIONES_ACCION_OK("TEST_ACCION_ACCION_OK",
			"Los tests de acciones para accion se han ejecutado correctamente"), //
	TEST_ATRIBUTOS_FUNCIONALIDAD_KO("TEST_ATRIBUTOS_FUNCIONALIDAD_KO",
			"Se ha producido un error al ejecutar los test de atributos para funcionalidad"), //
	TEST_ATRIBUTOS_FUNCIONALIDAD_OK("TEST_ATRIBUTOS_FUNCIONALIDAD_OK",
			"Los test de atributo para funcionalidad se han ejecutado correctamente"), //
	TEST_ACCIONES_FUNCIONALIDAD_KO("TEST_ACCIONES_FUNCIONALIDAD_KO",
			"Se ha producido un error al ejecutar los test de acciones para funcionalidad"), //
	TEST_ACCIONES_FUNCIONALIDAD_OK("TEST_ACCIONES_FUNCIONALIDAD_OK",
			"Los test de acciones para funcionalidad se han ejecutado correctamente"), //
	TEST_ATRIBUTOS_USUARIO_KO("TEST_ATRIBUTOS_USUARIO_KO",
			"Se ha producido un error al ejecutar los test de atributos para usuario"), //
	TEST_ATRIBUTOS_USUARIO_OK("TEST_ATRIBUTOS_USUARIO_OK",
			"Los test de atributo para usuario se han ejecutado correctamente"), //
	TEST_ACCIONES_USUARIO_KO("TEST_ACCIONES_USUARIO_KO",
			"Se ha producido un error al ejecutar los test de acciones para usuario"), //
	TEST_ACCIONES_USUARIO_OK("TEST_ACCIONES_USUARIO_OK",
			"Los test de acciones para usuario se han ejecutado correctamente"), //
	TEST_ACCIONES_PERSONA_KO("TEST_ACCIONES_PERSONA_KO",
			"Se ha producido un error al ejecutar los test de acciones para persona"), //
	TEST_ACCIONES_PERSONA_OK("TEST_ACCIONES_PERSONA_OK",
			"Los test de acciones para persona se han ejecutado correctamente"), //
	TEST_ATRIBUTOS_PERSONA_KO("TEST_ATRIBUTOS_PERSONA_KO",
			"Se ha producido un error al ejecutar los test de atributos para persona"), //
	TEST_ATRIBUTOS_PERSONA_OK("TEST_ATRIBUTOS_PERSONA_OK",
			"Los test de atributo para persona se han ejecutado correctamente"), //
	TEST_ACCIONES_RECUPERARPASS_KO("TEST_ACCIONES_RECUPERARPASS_KO",
			"Se ha producido un error al ejecutar los test de acciones para recuperar contraseña"), //
	TEST_ACCIONES_RECUPERARPASS_OK("TEST_ACCIONES_RECUPERARPASS_OK",
			"Los test de acciones para la recuperacion de contraseña se han ejecutado correctamente"), //
	TEST_ATRIBUTOS_RECUPERARPASS_KO("TEST_ATRIBUTOS_RECUPERARPASS_KO",
			"Se ha producido un error al ejecutar los test de atributos para recuperar contraseña"), //
	TEST_ATRIBUTOS_RECUPERARPASS_OK("TEST_ATRIBUTOS_RECUPERARPASS_OK",
			"Los test de atributo para la recuperacion de contraseña se han ejecutado correctamente"), //
	TEST_ACCIONES_EMPRESA_KO("TEST_ACCIONES_EMPRESA_KO",
			"Se ha producido un error al ejecutar los test de acciones para empresa"), //
	TEST_ACCIONES_EMPRESA_OK("TEST_ACCIONES_EMPRESA_OK",
			"Los test de acciones para la empresa se han ejecutado correctamente"), //
	TEST_ATRIBUTOS_EMPRESA_KO("TEST_ATRIBUTOS_EMPRESA_KO",
			"Se ha producido un error al ejecutar los test de atributos para empresa"), //
	TEST_ATRIBUTOS_EMPRESA_OK("TEST_ATRIBUTOS_EMPRESA_OK",
			"Los test de atributo para la empresa se han ejecutado correctamente");

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