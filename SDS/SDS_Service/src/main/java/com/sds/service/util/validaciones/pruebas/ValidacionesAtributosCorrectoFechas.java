package com.sds.service.util.validaciones.pruebas;

import org.apache.commons.lang3.StringUtils;

import com.sds.service.common.Constantes;
import com.sds.service.common.Mensajes;

public class ValidacionesAtributosCorrectoFechas {

	public String comprobarAtributoCorrectoFechas(final String atributo) {

		String resultado = StringUtils.EMPTY;

		if (atributo.matches(Constantes.FORMATO_FECHAS)) {
			resultado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;
		}

		return resultado;
	}
}
