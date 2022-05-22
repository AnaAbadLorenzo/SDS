package com.sds.service.util.validaciones.pruebas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.sds.service.common.CodigosMensajes;
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

	public String comprobarAnterioridadFecha(final String atributo) throws ParseException {
		String resultado = StringUtils.EMPTY;
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		final Date fechaIntroducidaUsuario = simpleDateFormat.parse(atributo);
		final Date fechaActual = new Date();

		if (fechaIntroducidaUsuario.compareTo(fechaActual) < 0) {
			resultado = CodigosMensajes.FECHA_INTRODUCIDA_MENOR_FECHA_ACTUAL + " - "
					+ Mensajes.FECHA_INTRODUCIDA_NO_PUEDE_SER_MENOR_QUE_FECHA_ACTUAL;
		} else {
			resultado = Mensajes.AVANZAR_SIGUIENTE_CAMPO;
		}

		return resultado;
	}
}
