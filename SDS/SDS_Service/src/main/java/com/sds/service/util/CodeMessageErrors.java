package com.sds.service.util;

public enum CodeMessageErrors {

	LOGIN_VACIO("El login es vacío"), //
	REGISTRO_VACIO("El registro está vacío"), //
	USUARIO_NO_ENCONTRADO_EXCEPTION("No se ha encontrado el usuario"), //
	PASSWORD_INCORRECTO_EXCEPTION("El password es incorrecto"), //
	USUARIO_YA_EXISTE_EXCEPTION("El usuario ya existe"), //
	PERSONA_YA_EXISTE_EXCEPTION("La persona ya existe"), //
	EMPRESA_YA_EXISTE_EXCEPTION("La empresa ya existe"), //
	JWT_EXPIRADO_EXCEPTION("JWT expirado"), //
	JWT_MALFORMADO_EXCEPTION("JWT malformado"), //
	JWT_NO_SOPORTADO_EXCEPTION("JWT no soportado"), //
	ROL_NO_EXISTE_EXCEPTION("El rol no existe"), //
	ROL_YA_EXISTE_EXCEPTION("El rol ya existe"), //
	NO_HAY_ROLES_EXCEPTION("No se han encontrado roles"), //
	ROL_ASOCIADO_USUARIO_EXCEPTION("Rol asociado a un usuario"), //
	ROL_VACIO("Rol vacío"), //
	LOG_EXCEPCIONES_VACIO("LogExcepciones vacío"), //
	LOG_ACCIONES_VACIO("LogAcciones vacío");

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