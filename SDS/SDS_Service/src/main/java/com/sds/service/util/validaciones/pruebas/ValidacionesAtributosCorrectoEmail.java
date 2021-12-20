package com.sds.service.util.validaciones.pruebas;

import org.apache.commons.lang3.StringUtils;

import com.sds.service.common.Constantes;
import com.sds.service.common.Mensajes;

public class ValidacionesAtributosCorrectoEmail {

	public String comprobarAtributoCorrectoEmail(final String atributo) {

		String resultado = StringUtils.EMPTY;

		if (atributo.matches(Constantes.FORMATO_EMAIL)) {
			resultado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;
		}

		return resultado;
	}
}
