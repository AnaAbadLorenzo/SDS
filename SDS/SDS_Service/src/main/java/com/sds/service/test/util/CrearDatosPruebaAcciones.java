package com.sds.service.test.util;

import java.util.Map;

import com.sds.service.test.model.DatosPruebaAcciones;

public class CrearDatosPruebaAcciones {

	public DatosPruebaAcciones createDatosPruebaAcciones(final String resultadoObtenido, final String resultadoEsperado,
			final String prueba, final String tipoPrueba, final Map<String, String> valor) {

		final DatosPruebaAcciones datosPruebaAcciones = new DatosPruebaAcciones();

		if (resultadoObtenido != null && resultadoEsperado.equals(resultadoObtenido)) {
			datosPruebaAcciones.setPrueba(prueba);
			datosPruebaAcciones.setValor(valor);
			datosPruebaAcciones.setResultadoEsperado(resultadoEsperado);
			datosPruebaAcciones.setResultadoObtenido(resultadoObtenido);
			datosPruebaAcciones.setTipoPrueba(tipoPrueba);
		}

		return datosPruebaAcciones;

	}
}
