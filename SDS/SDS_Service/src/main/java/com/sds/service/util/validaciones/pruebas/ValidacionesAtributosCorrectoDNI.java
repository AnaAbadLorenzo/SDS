package com.sds.service.util.validaciones.pruebas;

import org.apache.commons.lang3.StringUtils;

import com.sds.service.common.CodigosMensajes;
import com.sds.service.common.Constantes;
import com.sds.service.common.Mensajes;

public class ValidacionesAtributosCorrectoDNI {

	public String comprobarAtributoCorrectoDNI(final String atributo) {

		String resultado = StringUtils.EMPTY;

		if (atributo.matches(Constantes.FORMATO_DNI)) {
			resultado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;
		}

		return resultado;
	}

	public String comprobarAtributoDNIValido(final String atributo) {
		String resultado = StringUtils.EMPTY;
		final String letrasDNI = "TRWAGMYFPDXBNJZSQVHLCKET";

		if (atributo.matches(Constantes.FORMATO_DNI)) {
			final String dniSinLetras = atributo.substring(0, atributo.length() - 1);
			final String letraIntroUsuario = atributo.substring(atributo.length() - 1, atributo.length());
			final int posicion = Integer.parseInt(dniSinLetras) % 23;
			final String letra = letrasDNI.substring(posicion, posicion + 1);

			if (!(letra.equals(letraIntroUsuario))) {
				resultado = CodigosMensajes.DNI_PERSONA_NO_VALIDO + " - " + Mensajes.DNI_NO_ES_VALIDO;
			} else {
				resultado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;
			}
		}

		return resultado;
	}
}
