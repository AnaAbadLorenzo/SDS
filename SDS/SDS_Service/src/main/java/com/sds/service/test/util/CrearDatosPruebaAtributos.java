package com.sds.service.test.util;

import com.sds.service.test.model.DatosPruebaAtributos;

public class CrearDatosPruebaAtributos {

	public DatosPruebaAtributos createDatosPruebaAtributos(final String resultadoObtenido,
			final String resultadoEsperado, final String prueba, final String tipoPrueba, final String valor,
			final String campo) {

		final DatosPruebaAtributos datosPruebaAtributos = new DatosPruebaAtributos();

		if (resultadoObtenido != null && resultadoEsperado.equals(resultadoObtenido)) {
			datosPruebaAtributos.setCampo(campo);
			datosPruebaAtributos.setPrueba(prueba);
			datosPruebaAtributos.setValor(valor);
			datosPruebaAtributos.setResultadoEsperado(resultadoEsperado);
			datosPruebaAtributos.setResultadoObtenido(resultadoObtenido);
			datosPruebaAtributos.setTipoPrueba(tipoPrueba);
		}

		return datosPruebaAtributos;

	}
}
