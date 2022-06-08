package com.sds.service.test.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sds.service.test.model.DatosPruebaAtributos;

public class CrearDatosPruebaAtributos {

	public final static Logger LOGGER = LoggerFactory.getLogger(CrearDatosPruebaAtributos.class);

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

		LOGGER.debug("Resultado Obtenido '{}'", resultadoObtenido);
		LOGGER.debug("Resultado Esperado '{}'", resultadoEsperado);
		LOGGER.debug("Prueba '{}'", prueba);
		LOGGER.debug("Tipo Prueba '{}'", tipoPrueba);
		LOGGER.debug("Valor '{}'", valor);
		LOGGER.debug("Campo '{}'", campo);

		return datosPruebaAtributos;

	}
}
