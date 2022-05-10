package com.sds.service.util.validaciones.pruebas;

import org.apache.commons.lang3.StringUtils;

import com.sds.service.common.Constantes;
import com.sds.service.common.Mensajes;

public class ValidacionesAtributosTextoSignosPuntuacion {

	public String comprobarTextoSignosPuntuacion(final String atributo) {
		String resultado = StringUtils.EMPTY;

		if (atributo.matches(Constantes.FORMATO_TEXTO_SIGNOS_PUNTUACION)) {
			resultado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;
		}

		return resultado;

	}

}
