package com.sds.service.util.validaciones.pruebas;

import org.apache.commons.lang3.StringUtils;

import com.sds.service.common.Constantes;
import com.sds.service.common.Mensajes;

public class ValidacionesAtributosCorrectoAlfabetico {

	public String comprobarAtributoCorrectoAlfabetico(final String atributo) {
		String resultado = StringUtils.EMPTY;

		if (atributo.matches(Constantes.ALFABETICO)) {
			resultado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;
		}

		return resultado;
	}

}
