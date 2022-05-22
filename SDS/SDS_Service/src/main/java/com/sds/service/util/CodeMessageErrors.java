package com.sds.service.util;

public enum CodeMessageErrors {

	LOGIN_VACIO("El login es vacío"), //
	REGISTRO_VACIO("El registro está vacío"), //
	USUARIO_NO_ENCONTRADO_EXCEPTION("No se ha encontrado el usuario"), //
	USUARIO_AÑADIR_VACIO("Los datos de usuaio y/o persona son vacios"), //
	PASSWORD_INCORRECTO_EXCEPTION("El password es incorrecto"), //
	USUARIO_YA_EXISTE_EXCEPTION("El usuario ya existe"), //
	PERSONA_YA_EXISTE_EXCEPTION("La persona ya existe"), //
	EMPRESA_YA_EXISTE_EXCEPTION("La empresa ya existe"), //
	PERSONA_NO_EXISTE_EXCEPTION("La persona no existe"), //
	JWT_EXPIRADO_EXCEPTION("JWT expirado"), //
	JWT_MALFORMADO_EXCEPTION("JWT malformado"), //
	JWT_NO_SOPORTADO_EXCEPTION("JWT no soportado"), //
	ROL_NO_EXISTE_EXCEPTION("El rol no existe"), //
	ROL_YA_EXISTE_EXCEPTION("El rol ya existe"), //
	NO_HAY_ROLES_EXCEPTION("No se han encontrado roles"), //
	ROL_ASOCIADO_USUARIO_EXCEPTION("El rol tiene un usuario asociado"), //
	ROL_ASOCIADO_ACCION_FUNCIONALIDAD_EXCEPTION("El rol tiene asociado una accion y una funcionalidad"), //
	USUARIO_ASOCIADO_PERSONA_EXCEPTION("La persona está asociada a un usuario"), //
	EMPRESA_ASOCIADA_PERSONA_EXCEPTION("El email proporcionado no existe"), //
	EMAIL_NO_ENCONTRADO_EXCEPTION("La empresa contiene personas asociadas"), //
	ACCION_YA_EXISTE_EXCEPTION("La accion ya existe"), //
	ACCION_NO_EXISTE_EXCEPTION("La accion que busca no existe"), //
	ACCION_ASOCIADA_ROL_FUNCIONALIDAD_EXCEPTION("La accion esta asociada con una accion y una funcionalidad"), //
	USUARIO_MODIFICAR_VACIO("El suuario/rol a modificar es vacío"), //
	MAIL_NO_ENVIADO_EXCEPTION("Se ha producido un error al enviar el email"), //
	ROL_VACIO("Rol vacío"), //
	USUARIO_VACIO("Usuario vacío"), //
	PERSONA_VACIO("Persona vacia"), //
	EMPRESA_VACIO("Empresa vacia"), //
	ACCION_VACIA("Accion vacia"), //
	PASSWDUSUARIO_VACIA("Contraseña vacía"), //
	EMAIL_VACIO("Email vacío"), //
	EMPRESA_NO_ENCONTRADA_EXCEPTION("La empresa no existe"), //
	FUNCIONALIDAD_VACIA("Funcionalidad vacía"), //
	FUNCIONALIDAD_YA_EXISTE_EXCEPTION("La funcionalidad que busca ya existe"), //
	FUNCIONALIDAD_NO_EXISTE_EXCEPTION("La funcionalidad que busca no existe"), //
	FUNCIONALIDAD_ASOCIADA_ROL_ACCION_EXCEPTION("La funcionalidad está asociada a un rol y a una funcionalidad"), //
	PERMISO_NO_EXISTE_EXCEPTION("El permiso no existe"), //
	LOG_EXCEPCIONES_VACIO("LogExcepciones vacío"), //
	LOG_ACCIONES_VACIO("LogAcciones vacío"), //
	NOTICIA_YA_EXISTE_EXCEPTION("La noticia ya existe"), //
	NOTICIA_NO_EXISTE_EXCEPTION("La noticia no existe"), //
	NOTICIA_VACIA("El título y/o el texto de la noticia están vacíos"), //
	OBJETIVO_YA_EXISTE_EXCEPTION("El objetivo ya existe"), //
	OBJETIVO_NO_EXISTE_EXCEPTION("El objetivo no existe"), //
	OBJETIVO_ASOCIADO_PLAN_EXCEPTION("El objetivo se encuentra asociado a un plan"), //
	OBJETIVO_VACIO("El nombre y/o la descripción del objetivo están vacíos"), //
	RESPUESTA_POSIBLE_YA_EXISTE_EXCEPTION("La respuesta posible ya existe"), //
	RESPUESTA_POSIBLE_NO_EXISTE_EXCEPTION("La respuesta posible no existe"), //
	RESPUESTA_POSIBLE_ASOCIADA_PROCESO("La respuesta no puede eliminarse porque está asociada a un proceso"), //
	RESPUESTA_POSIBLE_VACIA("El texto y/o la fecha de la respuesta están vacíos"), //
	PLAN_YA_EXISTE_EXCEPTION("El plan ya existe"), //
	PLAN_NO_EXISTE_EXCEPTION("El plan no existe"), //
	PLAN_VACIO("El nombre, la descripción y/o la fecha del plan son vacíos"),
	FECHA_INTRODUCIDA_ANTERIOR_FECHA_ACTUAL("La fecha introducida por el usuario es anterior a la fecha actual");

	private String codigo;

	private static final String DEFAULT = "OTRO";

	private CodeMessageErrors(final String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public static CodeMessageErrors getTipoByCodigo(final String codigo) {
		for (final CodeMessageErrors tipoDoc : CodeMessageErrors.values()) {
			if (tipoDoc.getCodigo().equals(codigo)) {
				return tipoDoc;
			}
		}
		return null;
	}

	public static String getTipoNameByCodigo(final String codigo) {
		for (final CodeMessageErrors tipoDoc : CodeMessageErrors.values()) {
			if (tipoDoc.getCodigo().equals(codigo)) {
				return tipoDoc.name();
			}
		}
		return DEFAULT;
	}
}