package com.sds.model;

public enum RespCode {

	PARSE_EXCEPTION("PARSE_EXCEPTION", "Error al parsear los datos"), //
	FECHA_INTRODUDICA_ANTERIOR_FECHA_ACTUAL("FECHA_INTRODUDICA_ANTERIOR_FECHA_ACTUAL",
			"La fecha introducida por el usuario es anterior a la fecha actual"), //
	MENU_USUARIO_OK("MENU_USUARIO_OK", "Las funcionalidades del usuario se han listado correctamente"), //
	LOGIN_VACIO("LOGIN_VACIO", "Los datos de usuario y contraseña no pueden ser vacíos"), //
	USUARIO_NO_ENCONTRADO("USUARIO_NO_EXISTE", "El usuario no existe"), //
	PASSWORD_INCORRECTO("PASSWORD_INCORRECTO", "La contraseña no coincide con el usuario"), //
	PASSWORD_CAMBIADA("PASSWORD_CAMBIADA", "La contraseña ha sido cambiada correctamente"), //
	LOGIN_OK("LOGIN_OK", "Usuario logeado correctamente"), //
	ROL_VACIO("ROL_VACIO", "El nombre del rol y/o la descripcion no pueden ser vacios"), //
	ROL_MODIFICADO("ROL_MODIFICADO", "El rol se ha modificado correctamente"), //
	ROL_ELIMINADO("ROL_ELIMINADO", "El rol se ha eliminado correctamente"), //
	ROL_GUARDADO("ROL_GUARDADO", "El rol se ha guadado correctamente"), //
	ROL_BORRADO("ROL_BORRADO", "El rol se ha borrado correctamente"), //
	ROL_REACTIVADO("ROL_REACTIVADO", "El rol se ha reactivado correctamente"), //
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
	PERSONA_BORRADA("PERSONA_BORRADA", "La persona se ha borrado correctamente"), //
	PERSONA_ASOCIADA_EMPRESA("PERSONA_ASOCIADA_EMPRESA", "La persona se ha asociada a la empresa"), //
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
	EMPRESA_BORRADA("EMPRESA_BORRADA", "La empresa se ha borrado correctamente"), //
	EMPRESA_ASOCIADA_PERSONAS_EXCEPTION("EMPRESA_ASOCIADA_PERSONAS_EXCEPTION", "La empresa tiene personas asociadas"), //
	EMAIL_NO_ENCONTRADO("EMAIL_NO_ENCONTRADO", "El email no existe"), //
	RECUPERAR_PASS_VACIO("RECUPERAR_PASS_VACIO", "El email y/o el usuario para recuperar la contraseña están vacíos"), //
	EMAIL_VACIO("EMAIL_VACIO", "El email de usuario es vacío"), //
	USUARIO_VACIO("USUARIO_VACIO", "El usuario es vacío"), //
	USUARIO_GUARDADO("USUARIO_GUARDADO", "El usuario se ha guardado correctamente"), //
	USUARIO_ELIMINADO("USUARIO_ELIMINADO", "El usuario se ha eliminado correctamente"), //
	USUARIO_MODIFICADO("USUARIO_MODIFICADO", "El usuario se ha modificado correctamente"), //
	USUARIO_REACTIVADO("USUARIO_REACTIVADO", "El usuario se ha reactivado correctamente"), //
	USUARIO_BORRADO("USUARIO_BORRADO", "El usuario se ha borrado correctamente"), //
	USUARIO_MODIFICAR_VACIO("USUARIO_MODIFICAR_VACIO", "El usuario y/o el rol es vacio"), //
	RECUPERAR_PASS_OK("RECUPERAR_PASS_OK", "La nueva contraseña se ha enviado correctamente"), //
	USUARIO_ANADIR_VACIO("USUARIO_AÑADIR_VACIO", "Los datos de usuario y/o persona son vacios"), //
	ROL_YA_EXISTE_EXCEPTION("ROL_YA_EXISTE", "El rol ya existe"), //
	ROL_NO_EXISTE_EXCEPTION("ROL_NO_EXISTE", "El rol no existe"), //
	ROL_ASOCIADO_ACCION_FUNCIONALIDAD_EXCEPTION("ROL_ASOCIADO_ACCION_FUNCIONALIDAD_EXCEPTION",
			"El rol tiene asociado una accion y una funcionalidad"), //
	NO_HAY_ROLES_EXCEPTION("NO_HAY_ROLES", "No existe ningún rol registrado"), //
	ROL_ASOCIADO_USUARIO_EXCEPTION("ROL_ASOCIADO_USUARIO", "Hay un usuario que tiene asociado el rol a eliminar"), //
	ACCION_VACIA("ACCION_VACIA", "El nombre y/o la descripción de la acción están vacíos"), //
	ACCION_ENCONTRADA("ACCION_ENCONTRADA", "La acción que busca se ha encontrado correctamente"), //
	ACCIONES_LISTADAS("ACCIONES_LISTADAS", "Las acciones se han listdo correctamente"), //
	ACCIONES_ELIMINADAS_LISTADAS("ACCIONES_ELIMINADAS_LISTADAS",
			"Las acciones eliminadas se han listado correctamente"), //
	ACCION_GUARDADA("ACCION_GUARDADA", "La acción se ha guadado correctamente"), //
	ACCION_MODIFICADA("ACCION_MODIFICADA", "La acción se ha modificado correctamente"), //
	ACCION_ELIMINADA("ACCION_ELIMINADA", "La acción se ha eliminado correctamente"), //
	ACCION_ASIGNADA("ACCION_ASIGNADA", "La acción se ha asignado correctamente"), //
	ACCION_ASOCIADA_ROL_FUNCIONALIDAD_EXCEPTION("ACCION_ASOCIDADA_ROL_FUNCIONALIDAD",
			"La accion se encuentra asociada a una accion y funcionalidad"), //
	ACCION_YA_EXISTE_EXCEPTION("ACCION_YA_EXISTE", "La acción ya existe"), //
	ACCION_NO_EXISTE_EXCEPTION("ACCION_NO_EXISTE_EXCEPTION", "La accion que busca no existe"), //
	ACCION_REACTIVADA("ACCION_REACTIVADA", "La accion se ha reactivado correctamente"), //
	ACCION_REVOCADA("ACCION_REVOCADA", "La acción se ha revocado para el rol y la funcionalidad seleccionados"), //
	ACCION_BORRADA("ACCION_BORRADA", "La accion se ha borrado correctamente"), //
	ACCIONES_USUARIO_OK("ACCIONES_USUARIO_OK", "Las acciones del usuario se han recuperado correctamente"), //
	FUNCIONALIDAD_VACIA("FUNCIONALIDAD_VACIA", "La funcionalidad está vacía"), //
	FUNCIONALIDAD_ENCONTRADA("FUNCIONALIDAD_ENCONTRADA", "La funcionalidad se ha encontrado correctamente"), //
	FUNCIONALIDADES_LISTADAS("FUNCIONALIDADES_LISTADAS", "Las funcionalidades se han listado correctamente"), //
	FUNCIONALIDADES_ELIMINADAS_LISTADAS("FUNCIONALIDADES_ELIMINADAS_LISTADAS",
			"Las funcionalidades eliminadas se han listado correctamente"), //
	FUNCIONALIDAD_MODIFICADA("FUNCIONALIDAD_MODIFICADA", "La funcionalidad se ha modificado correctamente"), //
	FUNCIONALIDAD_ELIMINADA("FUNCIONALIDAD_ELIMINADA", "La funcionalidad se ha eliminado correctamente"), //
	FUNCIONALIDAD_ASOCIADA_ROL_ACCION_EXCEPTION("FUNCIONALIDAD_ASOCIADA_ROL_ACCION_EXCEPTION",
			"La funcionalidad está asociada a un rol y una accion"), //
	FUNCIONALIDAD_NO_EXISTE_EXCEPTION("FUNCIONALIDAD_NO_EXISTE_EXCEPTION", "La funcionalidad que busca no existe"), //
	FUNCIONALIDAD_YA_EXISTE_EXCEPTION("FUNCIONALIDAD_YA_EXISTE_EXCEPTION", "La funcionalidad que busca ya existe"), //
	FUNCIONALIDAD_GUARDADA("FUNCIONALIDAD_GUARDADA", "La funcionalidad se ha guardado correctamente"), //
	FUNCIONALIDAD_REACTIVADA("FUNCIONALIDAD_REACTIVADA", "La funcionalidad se ha reactivado correctamente"), //
	FUNCIONALIDAD_BORRADA("FUNCIONALIDAD_BORRADA", "La funcionalidad se ha borrado correctamente"), //
	PERMISO_NO_EXISTE_EXCEPTION("PERMISO_NO_EXISTE",
			"El rol no tiene permisos para realizar esa acción sobre esa funcionalidad"), //
	USUARIOS_LISTADOS("USUARIOS_LISTADOS", "Los usuarios se han listado correctamente"), //
	USUARIOS_ELIMINADOS_LISTADOS("USUARIOS_ELIMINADOS_LISTADOS",
			"Los usuarios eliminados se han listado correctamente"), //
	REGISTRO_OK("REGISTRO_OK", "Registro realizado correctamente"), //
	PERMISOS_OBTENIDOS("PERMISOS_OBTENIDOS", "Los permisos se han obtenido correctamente"), //
	ROL_USUARIO_MODIFICADO_OK("ROL_USUARIO_MODIFICADO_OK", "El rol del usuario se ha modificado correctamente"), //
	NOTICIAS_LISTADAS("NOTICIAS_LISTADAS", "Las noticias se han listado correctamente"), //
	NOTICIA_ENCONTRADA("NOTICIA_ENCONTRADA", "La noticia se ha encontrado correctamnete"), //
	NOTICIA_GUARDADA("NOTICIA_GUARDADA", "La noticia se ha guardado correctamente"), //
	NOTICIA_MODIFICADA("NOTICIA_MODIFICADA", "La noticia se ha modificado correctamente"), //
	NOTICIA_BORRADA("NOTICIA_BORRADA", "La noticia se ha borrado correctamente"), //
	NOTICIA_VACIA("NOTICIA_VACIA", "El titulo y/o el texto de la noticia son vacíos"), //
	NOTICIA_YA_EXISTE_EXCEPTION("NOTICIA_YA_EXISTE", "La noticia ya existe"), //
	OBJETIVOS_LISTADOS("OBJETIVOS_LISTADOS", "Los objetivos se han listado correctamente"), //
	OBJETIVO_ENCONTRADO("OBJETIVO_ENCONTRADO", "El objetivo se ha encontrado correctamente"), //
	OBJETIVOS_ELIMINADOS_LISTADOS("OBJETIVOS_ELIMINADOS_LISTADOS",
			"Los objetivos eliminados se han listado correctamente"), //
	OBJETIVO_GUARDADO("OBJETIVO_GUARDADO", "El objetivo se ha guardado correctamente"), //
	OBJETIVO_MODIFICADO("OBJETIVO_MODIFICADO", "El objetivo se ha modificado correctamente"), //
	OBJETIVO_ELIMINADO("OBJETIVO_ELIMINADO", "El objetivo se ha eliminado correctamente"), //
	OBJETIVO_REACTIVADO("OBJETIVO_REACTIVADO", "El objetivo se ha reactivado correctamente"), //
	OBJETIVO_BORRADO("OBJETIVO_BORRADO", "El objetivose ha borrado correctamente"), //
	OBJETIVO_VACIO("OBJETIVO_VACIO", "El objetivo está vacía"), //
	OBJETIVO_YA_EXISTE_EXCEPTION("OBJETIVO_YA_EXISTE_EXCEPTION", "El objetivo ya existe"), //
	OBJETIVO_NO_EXISTE_EXCEPTION("OBJETIVO_NO_EXISTE_EXCEPTION", "El objetivo no existe"), //
	OBJETIVO_ASOCIADO_PLAN_EXCEPTION("OBJETIVO_ASOCIADO_PLAN_EXCEPTION", "El objetivo se encuentra asociado a un plan"), //
	NOTICIA_NO_EXISTE_EXCEPTION("NOTICIA_NO_EXISTE", "La noticia no existe"), //
	RESPUESTA_POSIBLE_ECONTRADA("RESPUESTA_POSIBLE_ENCONTRADA",
			"Las respuestas posibles se han encontrado correctamente"), //
	RESPUESTAS_POSIBLES_LISTADAS("RESPUESTAS_POSIBLES_LISTADAS",
			"Las respuestas posibles se han listado correctamente"), //
	RESPUESTAS_POSIBLES_ELIMINADAS_LISTADAS("RESPUESTAS_POSIBLES_ELIMINADAS_LISTADAS",
			"Las respuestas posibles eliminadas se han listado correctamente"), //
	RESPUESTA_POSIBLE_GUARDADA("RESPUESTA_POSIBLE_GUARDADA", "La respuesta posible se ha guardado correctamente"), //
	RESPUESTA_POSIBLE_MODIFICADA("RESPUESTA_POSIBLE_MODIFICADA", "La respuesta posible se ha modificado correctamente"), //
	RESPUESTA_POSIBLE_ELIMINADA("RESPUESTA_POSIBLE_ELIMINADA", "La respuesta posible se ha eliminado correctamente"), //
	RESPUESTA_POSIBLE_REACTIVADA("RESPUESTA_POSIBLE_REACTIVADA", "La respuesta posible se ha reactivado correctamente"), //
	RESPUESTA_POSIBLE_BORRADA("RESPUESTA_POSIBLE_BORRADA", "La respuesta posible se ha borrado correctamente"), //
	RESPUESTA_POSIBLE_YA_EXISTE_EXCEPTION("RESPUESTA_POSIBLE_YA_EXISTE_EXCEPTION", "La respuesta posible ya existe"), //
	RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION("RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION", "La respuesta posible no existe"), //
	RESPUESTA_ASOCIADA_PROCESO_EXCEPTION("RESPUESTA_ASOCIADA_PROCESO_EXCEPTION",
			"La respuesta posible está asociada a un proceso"), //
	RESPUESTA_POSIBLE_VACIA("RESPUESTA_POSIBLE_VACIA", "El texto y/o fecha de la noticia son vacios"), //
	PLAN_ENCONTRADO("PLAN_ENCONTRADO", "El plan se ha encontrado correctamente"), //
	PLANES_LISTADOS("PLANES_LISTADOS", "Los planes se han listado correctamente"), //
	PLANES_ELIMINADOS_LISTADOS("PLANES_ELIMINADOS_LISTADOS", "Los planes eliminados se han listado correctamente"), //
	PLAN_GUARDADO("PLAN_GUARDADO", "El plan se ha guardado correctamente"), //
	PLAN_MODIFICADO("PLAN_MODIFICADO", "El plan se ha modificado correctamente"), //
	PLAN_ELIMINADO("PLAN_ELIMINADO", "El plan se ha eliminado correctamente"), //
	PLAN_REACTIVADO("PLAN_REACTIVADO", "El plan se ha reactivado correctamente"), //
	PLAN_BORRADO("PLAN_BORRADO", "El plan se ha borrado correctamente"), //
	PLAN_YA_EXISTE_EXCEPTION("PLAN_YA_EXISTE_EXCEPTION", "El plan ya existe"), //
	PLAN_NO_EXISTE_EXCEPTION("PLAN_NO_EXISTE_EXCEPTION", "El plan no existe"), //
	PLAN_VACIO("PLAN_VACIO", "El nombre, la descripción y/o la fecha del plan están vacíos"), //
	LOG_EXCEPCIONES_LISTADOS("LOG_EXCEPCIONES_LISTADOS", "Los logs de excepciones se han listado correctamente"), //
	LOG_ACCIONES_LISTADOS("LOG_ACCIONES_LISTADOS", "Los logs de acciones se han listado correctamente"), //
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
			"Los test de atributo para la empresa se han ejecutado correctamente"), //
	TEST_ATRIBUTOS_NOTICIA_OK("TEST_ATRIBUTOS_NOTICIA_OK",
			"Los test de atributo para las noticias se han ejecutado correctamente"), //
	TEST_ATRIBUTOS_NOTICIA_KO("TEST_ATRIBUTOS_NOTICIA_KO",
			"Se ha producido un error al ejecutar los test de atributos para la noticia"), //
	TEST_ACCIONES_NOTICIA_KO("TEST_ACCIONES_NOTICIA_KO",
			"Se ha producido un error al ejecutar los test de acciones para la noticia"), //
	TEST_ACCIONES_NOTICIA_OK("TEST_ACCIONES_NOTICIA_OK",
			"Los test de acciones para la noticia se han ejecutado correctamente"), //
	TEST_ATRIBUTOS_OBJETIVO_OK("TEST_ATRIBUTOS_OBJETIVO_OK",
			"Los test de atributo para los objetivos se han ejecutado correctamente"), //
	TEST_ATRIBUTOS_OBJETIVO_KO("TEST_ATRIBUTOS_OBJETIVO_KO",
			"Se ha producido un error al ejecutar los test de atributos para los objetivos"), //
	TEST_ACCIONES_OBJETIVO_KO("TEST_ACCIONES_OBJETIVO_KO",
			"Se ha producido un error al ejecutar los test de acciones para los objetivos"), //
	TEST_ACCIONES_OBJETIVO_OK("TEST_ACCIONES_OBJETIVO_OK",
			"Los test de acciones para el objetivo se han ejecutado correctamente"), //
	TEST_ATRIBUTOS_RESPUESTA_POSIBLE_OK("TEST_ATRIBUTOS_RESPUESTA_POSIBLE_OK",
			"Los test de atributo para las respuestas posibles se han ejecutado correctamente"), //
	TEST_ATRIBUTOS_RESPUESTA_POSIBLE_KO("TEST_ATRIBUTOS_RESPUESTA_POSIBLE_KO",
			"Se ha producido un error al ejecutar los test de atributos para las respuestas posibles"), //
	TEST_ACCIONES_RESPUESTA_POSIBLE_KO("TEST_ACCIONES_RESPUESTA_POSIBLE_KO",
			"Se ha producido un error al ejecutar los test de acciones para las respuestas posibles"), //
	TEST_ACCIONES_RESPUESTA_POSIBLE_OK("TEST_ACCIONES_RESPUESTA_POSIBLE_OK",
			"Los test de acciones para las respuestas posibles se han ejecutado correctamente"),
	TEST_ATRIBUTOS_PLAN_OK("TEST_ATRIBUTOS_PLAN_OK",
			"Los test de atributo para los planes se han ejecutado correctamente"), //
	TEST_ATRIBUTOS_PLAN_KO("TEST_ATRIBUTOS_PLAN_KO",
			"Se ha producido un error al ejecutar los test de atributos para los planes"), //
	TEST_ACCIONES_PLAN_KO("TEST_ACCIONES_PLAN_KO",
			"Se ha producido un error al ejecutar los test de acciones para los planes"), //
	TEST_ACCIONES_PLAN_OK("TEST_ACCIONES_PLAN_OK",
			"Los test de acciones para los planes se han ejecutado correctamente");

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