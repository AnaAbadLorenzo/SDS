package com.sds.service.util;

public enum CodeMessageErrors {

	LOGIN_VACIO("LOGIN_VACIO"), //
	REGISTRO_VACIO("REGISTRO_VACIO"), //
	USUARIO_NO_ENCONTRADO_EXCEPTION("USUARIO_NO_ENCONTRADO_EXCEPTION"), //
	PASSWORD_INCORRECTO_EXCEPTION("PASSWORD_INCORRECTO_EXCEPTION"), //
	USUARIO_YA_EXISTE_EXCEPTION("USUARIO_YA_EXISTE_EXCEPTION"), //
	PERSONA_YA_EXISTE_EXCEPTION("PERSONA_YA_EXISTE_EXCEPTION"), //
	EMPRESA_YA_EXISTE_EXCEPTION("EMPRESA_YA_EXISTE_EXCEPTION"), //
	JWT_EXPIRADO_EXCEPTION("JWT_EXPIRADO_EXCEPTION"), //
	JWT_MALFORMADO_EXCEPTION("JWT_MALFORMADO_EXCEPTION"), //
	JWT_NO_SOPORTADO_EXCEPTION("JWT_NO_SOPORTADO_EXCEPTION");

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