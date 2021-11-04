package com.sds.service.util;

public enum CodeMessageErrors {

	LOGIN_VACIO("00001"), //
	USUARIO_NO_ENCONTRADO_EXCEPTION("00002"), //
	PASSWORD_INCORRECTO_EXCEPTION("00003");

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